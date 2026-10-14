/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FilmDAO;
import dao.PenontonDAO;
import dao.StudioDAO;
import dao.TiketDAO;
import dao.TransaksiDAO;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.Admin;
import model.Film;
import model.Penonton;
import model.Studio;
import model.Tiket;
import model.Transaksi;
import view.BioskopView;

/**
 *
 * @author ADVAN
 */
public class BioskopController {

    private final BioskopView view = new BioskopView();

    private final Admin admin = new Admin(
            "Cinematter",
            "cinematter@gmail.com",
            "081234567890",
            "Cinematter111"
    );

    private final FilmDAO filmDAO = new FilmDAO();
    private final StudioDAO studioDAO = new StudioDAO();
    private final PenontonDAO penontonDAO = new PenontonDAO();
    private final TiketDAO tiketDAO = new TiketDAO();
    private final TransaksiDAO transaksiDAO = new TransaksiDAO();

    public void jalankan() {
        view.header();

        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Penonton");
            System.out.println("3. Daftar Penonton");
            System.out.println("0. Keluar");

            switch (view.bacaPilihan("Pilih menu: ", 0, 3)) {
                case 1 -> loginAdmin();
                case 2 -> loginPenonton();
                case 3 -> daftarPenonton();
                case 0 -> {
                    berjalan = false;
                    view.pesan("Program selesai. Terima kasih.");
                }
            }
        }
    }

    private void loginAdmin() {
        String email = view.bacaEmail("Email admin: ");
        String password = view.bacaString("Password: ");

        if (admin.login(email, password)) {
            view.pesan("Login admin berhasil.");
            menuAdmin();
        } else {
            view.pesan("Email atau password admin salah.");
        }
    }

    private void loginPenonton() {
        String email = view.bacaEmail("Email: ");
        String password = view.bacaString("Password: ");

        try {
            Penonton penonton = penontonDAO.login(email, password);

            if (penonton == null) {
                view.pesan("Email atau password salah.");
                return;
            }

            view.pesan("Login berhasil. Selamat datang, "
                    + penonton.getNama() + ".");

            menuPenonton(penonton);

        } catch (SQLException e) {
            tampilError("login penonton", e);
        }
    }

    private void daftarPenonton() {
        String nama = view.bacaNama("Nama: ");
        String email = view.bacaEmail("Email: ");
        String noHP = view.bacaNoHP("Nomor HP: ");
        String password = view.bacaPassword("Password: ");

        try {
            if (penontonDAO.cariEmail(email) != null) {
                view.pesan("Email sudah terdaftar.");
                return;
            }

            Penonton penonton = new Penonton(
                    nama, email, noHP, password
            );

            penontonDAO.tambah(penonton);

            view.pesan("Pendaftaran berhasil. Data tersimpan di database.");

        } catch (SQLException e) {
            tampilError("mendaftarkan penonton", e);
        }
    }

    private void menuAdmin() {
        boolean keluar = false;

        while (!keluar) {
            admin.tampilkanMenu();

            switch (view.bacaPilihan("Pilih menu: ", 0, 5)) {
                case 1 -> menuFilm();
                case 2 -> menuStudio();
                case 3 -> menuTiket();
                case 4 -> lihatPenonton();
                case 5 -> lihatTransaksi();
                case 0 -> keluar = true;
            }
        }
    }

    private void menuPenonton(Penonton penonton) {
        boolean keluar = false;

        while (!keluar) {
            penonton.tampilkanMenu();

            switch (view.bacaPilihan("Pilih menu: ", 0, 5)) {
                case 1 -> tampilProfil(penonton);
                case 2 -> lihatFilm();
                case 3 -> lihatStudio();
                case 4 -> beliTiket(penonton);
                case 5 -> lihatRiwayat(penonton);
                case 0 -> keluar = true;
            }
        }
    }

    private void tampilProfil(Penonton penonton) {
        System.out.println("\n========== PROFIL PENONTON ==========");
        System.out.println("Nama  : " + penonton.getNama());
        System.out.println("Email : " + penonton.getEmail());
        System.out.println("No HP : " + penonton.getNoHP());
    }

    private void menuFilm() {
        boolean kembali = false;

        while (!kembali) {
            System.out.println("\n========== KELOLA FILM ==========");
            System.out.println("1. Tambah Film");
            System.out.println("2. Lihat Film");
            System.out.println("3. Update Film");
            System.out.println("4. Hapus Film");
            System.out.println("0. Kembali");

            switch (view.bacaPilihan("Pilih menu: ", 0, 4)) {
                case 1 -> tambahFilm();
                case 2 -> lihatFilm();
                case 3 -> updateFilm();
                case 4 -> hapusFilm();
                case 0 -> kembali = true;
            }
        }
    }

    private void tambahFilm() {
        Film film = new Film(
                view.bacaString("Judul film: "),
                view.bacaGenre(),
                view.bacaPositif("Durasi (menit): "),
                view.bacaHarga("Harga tiket: Rp")
        );

        try {
            filmDAO.tambah(film);
            view.pesan("Film berhasil ditambahkan ke database.");
        } catch (SQLException e) {
            tampilError("menambahkan film", e);
        }
    }

    private List<Film> ambilFilm() {
        try {
            return filmDAO.getSemua();
        } catch (SQLException e) {
            tampilError("membaca film", e);
            return Collections.emptyList();
        }
    }

    private void lihatFilm() {
        List<Film> daftar = ambilFilm();

        System.out.println("\n========== DAFTAR FILM ==========");

        if (daftar.isEmpty()) {
            view.pesan("Data film kosong. Periksa database.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilFilm(daftar.get(i), i + 1);
        }
    }

    private void updateFilm() {
        List<Film> daftar = ambilFilm();

        if (daftar.isEmpty()) {
            view.pesan("Tidak ada film untuk diperbarui.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilFilm(daftar.get(i), i + 1);
        }

        Film film = daftar.get(
                view.bacaPilihan("Pilih nomor film: ", 1, daftar.size()) - 1
        );

        film.setJudul(view.bacaString("Judul baru: "));
        film.setGenre(view.bacaGenre());
        film.setDurasi(view.bacaPositif("Durasi baru: "));
        film.setHarga(view.bacaHarga("Harga baru: Rp"));

        try {
            filmDAO.update(film.getIdFilm(), film);
            view.pesan("Data film berhasil diperbarui.");
        } catch (SQLException e) {
            tampilError("memperbarui film", e);
        }
    }

    private void hapusFilm() {
        List<Film> daftar = ambilFilm();

        if (daftar.isEmpty()) {
            view.pesan("Tidak ada film untuk dihapus.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilFilm(daftar.get(i), i + 1);
        }

        Film film = daftar.get(
                view.bacaPilihan("Pilih nomor film: ", 1, daftar.size()) - 1
        );

        if (!view.konfirmasi("Hapus film " + film.getJudul() + "?")) {
            return;
        }

        try {
            filmDAO.hapus(film.getIdFilm());
            view.pesan("Film berhasil dihapus.");
        } catch (SQLException e) {
            tampilError(
                    "menghapus film. Film yang sudah dipakai tiket "
                    + "tidak dapat dihapus", e
            );
        }
    }

    private void menuStudio() {
        boolean kembali = false;

        while (!kembali) {
            System.out.println("\n========== KELOLA STUDIO ==========");
            System.out.println("1. Tambah Studio");
            System.out.println("2. Lihat Studio");
            System.out.println("3. Update Studio");
            System.out.println("4. Hapus Studio");
            System.out.println("0. Kembali");

            switch (view.bacaPilihan("Pilih menu: ", 0, 4)) {
                case 1 -> tambahStudio();
                case 2 -> lihatStudio();
                case 3 -> updateStudio();
                case 4 -> hapusStudio();
                case 0 -> kembali = true;
            }
        }
    }

    private void tambahStudio() {
        Studio studio = new Studio(
                view.bacaString("Nama studio: "),
                view.bacaPositif("Kapasitas studio: "),
                view.bacaTipeStudio()
        );

        try {
            studioDAO.tambah(studio);
            view.pesan("Studio berhasil ditambahkan.");
        } catch (SQLException e) {
            tampilError("menambahkan studio", e);
        }
    }

    private List<Studio> ambilStudio() {
        try {
            return studioDAO.getSemua();
        } catch (SQLException e) {
            tampilError("membaca studio", e);
            return Collections.emptyList();
        }
    }

    private void lihatStudio() {
        List<Studio> daftar = ambilStudio();

        System.out.println("\n========== DAFTAR STUDIO ==========");

        if (daftar.isEmpty()) {
            view.pesan("Data studio kosong.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilStudio(daftar.get(i), i + 1);
        }
    }

    private void updateStudio() {
        List<Studio> daftar = ambilStudio();

        if (daftar.isEmpty()) {
            view.pesan("Tidak ada studio untuk diperbarui.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilStudio(daftar.get(i), i + 1);
        }

        Studio studio = daftar.get(
                view.bacaPilihan("Pilih nomor studio: ", 1, daftar.size()) - 1
        );

        studio.setNama(view.bacaString("Nama studio baru: "));
        studio.setKapasitas(view.bacaPositif("Kapasitas baru: "));
        studio.setTipe(view.bacaTipeStudio());

        try {
            studioDAO.update(studio.getIdStudio(), studio);
            view.pesan("Data studio berhasil diperbarui.");
        } catch (SQLException e) {
            tampilError("memperbarui studio", e);
        }
    }

    private void hapusStudio() {
        List<Studio> daftar = ambilStudio();

        if (daftar.isEmpty()) {
            view.pesan("Tidak ada studio untuk dihapus.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilStudio(daftar.get(i), i + 1);
        }

        Studio studio = daftar.get(
                view.bacaPilihan("Pilih nomor studio: ", 1, daftar.size()) - 1
        );

        if (!view.konfirmasi("Hapus " + studio.getNama() + "?")) {
            return;
        }

        try {
            studioDAO.hapus(studio.getIdStudio());
            view.pesan("Studio berhasil dihapus.");
        } catch (SQLException e) {
            tampilError(
                    "menghapus studio. Studio yang sudah dipakai tiket "
                    + "tidak dapat dihapus", e
            );
        }
    }

    private void menuTiket() {
        boolean kembali = false;

        while (!kembali) {
            System.out.println("\n========== KELOLA TIKET ==========");
            System.out.println("1. Tambah Tiket");
            System.out.println("2. Lihat Tiket");
            System.out.println("3. Update Tiket");
            System.out.println("4. Hapus Tiket");
            System.out.println("0. Kembali");

            switch (view.bacaPilihan("Pilih menu: ", 0, 4)) {
                case 1 -> tambahTiketAdmin();
                case 2 -> lihatTiket();
                case 3 -> updateTiket();
                case 4 -> hapusTiket();
                case 0 -> kembali = true;
            }
        }
    }

    private void tambahTiketAdmin() {
        try {
            List<Penonton> daftar = penontonDAO.getSemua();

            if (daftar.isEmpty()) {
                view.pesan("Belum ada penonton yang terdaftar.");
                return;
            }

            tampilPenonton(daftar);

            Penonton penonton = daftar.get(
                    view.bacaPilihan(
                            "Pilih nomor penonton: ", 1, daftar.size()
                    ) - 1
            );

            buatTiket(penonton);

        } catch (SQLException e) {
            tampilError("membaca data penonton", e);
        }
    }

    private void buatTiket(Penonton penonton) {
        List<Film> filmList = ambilFilm();
        List<Studio> studioList = ambilStudio();

        if (filmList.isEmpty() || studioList.isEmpty()) {
            view.pesan("Film atau studio belum tersedia.");
            return;
        }

        for (int i = 0; i < filmList.size(); i++) {
            view.tampilFilm(filmList.get(i), i + 1);
        }

        Film film = filmList.get(
                view.bacaPilihan("Pilih nomor film: ", 1, filmList.size()) - 1
        );

        for (int i = 0; i < studioList.size(); i++) {
            view.tampilStudio(studioList.get(i), i + 1);
        }

        Studio studio = studioList.get(
                view.bacaPilihan(
                        "Pilih nomor studio: ", 1, studioList.size()
                ) - 1
        );

        int jumlah = view.bacaPositif("Jumlah tiket: ");

        if (jumlah > studio.getKapasitas()) {
            view.pesan("Jumlah tiket melebihi kapasitas studio.");
            return;
        }

        try {
            Tiket tiket = new Tiket(penonton, film, studio, jumlah);
            tiketDAO.tambah(tiket);

            view.pesan("Tiket berhasil disimpan ke database.");

        } catch (SQLException e) {
            tampilError("menambahkan tiket", e);
        }
    }

    private List<Tiket> ambilTiket() {
        try {
            return tiketDAO.getSemua();
        } catch (SQLException e) {
            tampilError("membaca tiket", e);
            return Collections.emptyList();
        }
    }

    private void lihatTiket() {
        List<Tiket> daftar = ambilTiket();

        System.out.println("\n========== DAFTAR TIKET ==========");

        if (daftar.isEmpty()) {
            view.pesan("Belum ada tiket.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilTiket(daftar.get(i), i + 1);
        }
    }

    private void updateTiket() {
        List<Tiket> daftar = ambilTiket();

        if (daftar.isEmpty()) {
            view.pesan("Belum ada tiket untuk diperbarui.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilTiket(daftar.get(i), i + 1);
        }

        Tiket tiket = daftar.get(
                view.bacaPilihan("Pilih nomor tiket: ", 1, daftar.size()) - 1
        );

        List<Film> filmList = ambilFilm();
        List<Studio> studioList = ambilStudio();

        if (filmList.isEmpty() || studioList.isEmpty()) {
            view.pesan("Film atau studio belum tersedia.");
            return;
        }

        for (int i = 0; i < filmList.size(); i++) {
            view.tampilFilm(filmList.get(i), i + 1);
        }

        Film film = filmList.get(
                view.bacaPilihan("Pilih nomor film: ", 1, filmList.size()) - 1
        );

        for (int i = 0; i < studioList.size(); i++) {
            view.tampilStudio(studioList.get(i), i + 1);
        }

        Studio studio = studioList.get(
                view.bacaPilihan(
                        "Pilih nomor studio: ", 1, studioList.size()
                ) - 1
        );

        int jumlah = view.bacaPositif("Jumlah tiket: ");

        if (jumlah > studio.getKapasitas()) {
            view.pesan("Jumlah tiket melebihi kapasitas studio.");
            return;
        }

        tiket.setFilm(film);
        tiket.setStudio(studio);
        tiket.setJumlah(jumlah);

        try {
            tiketDAO.update(tiket.getIdTiket(), tiket);
            view.pesan("Tiket berhasil diperbarui.");
        } catch (SQLException e) {
            tampilError("memperbarui tiket", e);
        }
    }

    private void hapusTiket() {
        List<Tiket> daftar = ambilTiket();

        if (daftar.isEmpty()) {
            view.pesan("Belum ada tiket untuk dihapus.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            view.tampilTiket(daftar.get(i), i + 1);
        }

        Tiket tiket = daftar.get(
                view.bacaPilihan("Pilih nomor tiket: ", 1, daftar.size()) - 1
        );

        if (!view.konfirmasi("Hapus tiket ini?")) {
            return;
        }

        try {
            tiketDAO.hapus(tiket.getIdTiket());
            view.pesan("Tiket berhasil dihapus.");
        } catch (SQLException e) {
            tampilError(
                    "menghapus tiket. Tiket yang sudah memiliki transaksi "
                    + "tidak dapat dihapus", e
            );
        }
    }

    private void lihatPenonton() {
        try {
            List<Penonton> daftar = penontonDAO.getSemua();

            System.out.println("\n========== DAFTAR PENONTON ==========");

            if (daftar.isEmpty()) {
                view.pesan("Belum ada penonton.");
                return;
            }

            tampilPenonton(daftar);

        } catch (SQLException e) {
            tampilError("membaca data penonton", e);
        }
    }

    private void tampilPenonton(List<Penonton> daftar) {
        for (int i = 0; i < daftar.size(); i++) {
            Penonton p = daftar.get(i);

            System.out.printf(
                    "%n[%d] ID: %d | Nama: %s | Email: %s | No HP: %s%n",
                    i + 1, p.getIdPenonton(),
                    p.getNama(), p.getEmail(), p.getNoHP()
            );
        }
    }

    private void beliTiket(Penonton penonton) {
        List<Film> filmList = ambilFilm();
        List<Studio> studioList = ambilStudio();

        if (filmList.isEmpty() || studioList.isEmpty()) {
            view.pesan("Film atau studio belum tersedia.");
            return;
        }

        for (int i = 0; i < filmList.size(); i++) {
            view.tampilFilm(filmList.get(i), i + 1);
        }

        Film film = filmList.get(
                view.bacaPilihan("Pilih nomor film: ", 1, filmList.size()) - 1
        );

        for (int i = 0; i < studioList.size(); i++) {
            view.tampilStudio(studioList.get(i), i + 1);
        }

        Studio studio = studioList.get(
                view.bacaPilihan(
                        "Pilih nomor studio: ", 1, studioList.size()
                ) - 1
        );

        int jumlah = view.bacaPositif("Jumlah tiket: ");

        if (jumlah > studio.getKapasitas()) {
            view.pesan("Jumlah tiket melebihi kapasitas studio.");
            return;
        }

        try {
            Tiket tiket = new Tiket(penonton, film, studio, jumlah);

            tiketDAO.tambah(tiket);

            String kode = "TRX-" + System.currentTimeMillis();

            Transaksi transaksi = new Transaksi(
                    kode, penonton, tiket
            );

            transaksiDAO.tambah(transaksi);

            transaksi.tampilkanPembayaran();

            view.pesan("Pembelian berhasil disimpan ke database.");

        } catch (SQLException e) {
            tampilError("memproses pembelian tiket", e);
        }
    }

    private void lihatTransaksi() {
        try {
            List<Transaksi> daftar = transaksiDAO.getSemua();

            System.out.println("\n========== DAFTAR TRANSAKSI ==========");

            if (daftar.isEmpty()) {
                view.pesan("Belum ada transaksi.");
                return;
            }

            for (int i = 0; i < daftar.size(); i++) {
                view.tampilTransaksi(daftar.get(i), i + 1);
            }

        } catch (SQLException e) {
            tampilError("membaca transaksi", e);
        }
    }

    private void lihatRiwayat(Penonton penonton) {
        try {
            List<Transaksi> daftar =
                    transaksiDAO.getByPenontonId(penonton.getIdPenonton());

            System.out.println("\n========== RIWAYAT PEMBELIAN ==========");

            if (daftar.isEmpty()) {
                view.pesan("Belum ada riwayat transaksi.");
                return;
            }

            for (int i = 0; i < daftar.size(); i++) {
                view.tampilTransaksi(daftar.get(i), i + 1);
            }

        } catch (SQLException e) {
            tampilError("membaca riwayat pembelian", e);
        }
    }

    private void tampilError(String proses, Exception e) {
        System.out.println("Gagal " + proses + ": " + e.getMessage());
        System.out.println(
                "Periksa koneksi MySQL, nama database, "
                + "struktur tabel, dan relasi data."
        );
    }
}