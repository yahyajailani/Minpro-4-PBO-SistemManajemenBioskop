### Nama : Yahya Jailani
### NIM : 2509116085
### Kelas : C
    
 # Sistem Manajemen Bioskop

## Deskripsi Program

**Sistem Manajemen Bioskop** merupakan program berbasis Java yang digunakan untuk mengelola data film, studio, tiket, penonton, dan transaksi pembelian tiket.

Program memiliki dua jenis pengguna, yaitu **Admin** dan **Penonton**. Admin dapat mengelola data film, studio, dan tiket melalui fitur CRUD. Admin juga dapat melihat data penonton dan riwayat transaksi.

Penonton dapat melakukan registrasi dan login, melihat profil, melihat daftar film dan studio, membeli tiket, serta melihat riwayat transaksi.

Pada Mini Project 4, penyimpanan data yang sebelumnya menggunakan 'ArrayList' dikembangkan menjadi **database MySQL (MariaDB)**. Program terhubung ke database menggunakan **JDBC** dengan **Prepared Statement** dan pola **DAO (Data Access Object)**, sehingga data tersimpan permanen dan tidak hilang saat program ditutup.

Program juga dilengkapi dengan validasi input agar data yang dimasukkan sesuai dengan ketentuan sistem. Contohnya validasi nama, email, nomor HP, password, angka, pilihan menu, tipe studio, dan kapasitas studio.

Harga tiket ditentukan berdasarkan harga yang terdapat pada data film. Penonton tidak dapat memasukkan harga sendiri. Jika memilih studio bertipe **VIP**, sistem otomatis menambahkan biaya sebesar **Rp10.000**.

Data awal (dummy data) diisi melalui file **.sql** pada database, bukan melalui kode program, sehingga saat fitur Read dijalankan data langsung tampil.

---

## Teknologi yang Digunakan

* Java
* Apache NetBeans
* Maven
* MySQL / MariaDB
* Laragon (phpMyAdmin)
* JDBC (mysql-connector-j)
* Prepared Statement
* DAO (Data Access Object)
* Object-Oriented Programming (OOP)
* MVC (Model-View-Controller)
* Interface

---

## Struktur Project

'''text
src/main/java/
│
├── main/
│   └── Main.java
│
├── config/
│   └── DatabaseConnection.java
│
├── controller/
│   └── BioskopController.java
│
├── dao/
│   ├── PenontonDAO.java
│   ├── FilmDAO.java
│   ├── StudioDAO.java
│   ├── TiketDAO.java
│   └── TransaksiDAO.java
│
├── model/
│   ├── Pengguna.java
│   ├── Admin.java
│   ├── Penonton.java
│   ├── Film.java
│   ├── Studio.java
│   ├── Tiket.java
│   ├── Transaksi.java
│   └── Pembayaran.java (Interface)
│
└── view/
    └── BioskopView.java

'''
<img width="500" alt="Screenshot 2026-10-14 115418" src="https://github.com/user-attachments/assets/af0ddfad-c99d-4486-b096-ae6ca5fdd18d" />

Struktur tersebut membagi program menjadi bagian **Model, View, dan Controller**, ditambah **DAO** untuk akses database dan **config** untuk koneksi database, sehingga setiap bagian memiliki fungsi masing-masing.

---

# Alur Program

## 1. Menu Utama

Saat program dijalankan, pengguna akan melihat menu:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/2d6f404c-0112-40b0-802c-03221b38e5f7" />


Pengguna dapat memilih sesuai dengan kebutuhan.

---

## 2. Login Admin

Admin dapat login menggunakan akun bawaan yang sudah ditentukan di dalam program (akun admin tidak disimpan di database):

<img width="163" height="41" alt="Screenshot 2026-10-14 120448" src="https://github.com/user-attachments/assets/e0bdbb2d-3f8c-4516-a865-41fe2ac3d0bf" />

Jika login berhasil, Admin masuk ke menu Admin.

<img width="149" height="80" alt="Screenshot 2026-10-14 115847" src="https://github.com/user-attachments/assets/f0c746c2-74d2-48e3-815b-5f3faf658fc0" />

Admin dapat melakukan CRUD pada data film, studio, dan tiket yang tersedia di database, serta melihat data penonton dan transaksi. Ketika Admin memilih fitur Read (misalnya melihat data film atau studio), data dummy dari database langsung tampil, misalnya film Avenger: Endgame, Interstellar, Pabrik Rambut, dan Inside Out, tanpa perlu input dari awal.

---

## 3. Registrasi Penonton

Penonton yang belum mempunyai akun dapat melakukan registrasi dengan mengisi:

* Nama
* Email
* Nomor HP
* Password

Setiap data akan divalidasi sebelum disimpan. Program juga memeriksa apakah email sudah terdaftar. Jika valid, data disimpan ke tabel 'penonton' melalui 'PenontonDAO'.

Contohnya:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/1367a12b-ecb7-41a2-b26c-59631dc4079d" />


akan ditolak karena nomor HP harus 8-20 karakter berupa angka atau tanda +.

Email juga diperiksa agar mengikuti format yang sesuai, nama hanya boleh berisi huruf dan spasi, dengan panjang minimal 1 karakter, dan password minimal 6 karakter.

---

## 4. Login Penonton

Setelah melakukan registrasi, penonton dapat login menggunakan email dan password. Data login dicocokkan dengan data pada database.

Jika berhasil, penonton akan masuk ke menu:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/747ab803-05c6-4f07-9077-0906dff9c61f" />

---

## 5. Pembelian Tiket

Pada proses pembelian tiket, penonton memilih:

1. Film
2. Studio
3. Jumlah tiket

Daftar film dan studio diambil dari database. Harga tiket tidak dimasukkan oleh penonton karena sudah berasal dari data film.

Contohnya:

'''text
Harga Film = Rp50.000
'''

Jika memilih:

'''text
Studio Regular
'''

'''text
diskon : 5%
'''
maka harga tiket:

'''text
Rp47.500
'''
<img width="500" alt="image" src="https://github.com/user-attachments/assets/8c21c53e-d675-43a7-9c10-345fced24242" />


Sedangkan jika memilih:

'''text
Studio VIP
'''

maka sistem otomatis menghitung:

'''text
Rp50.000 + Rp10.000 = Rp60.000 + 5% = Rp57.000
'''
<img width="500" alt="image" src="https://github.com/user-attachments/assets/7a719158-dd5e-4469-8baf-1d5a493fa3a0" />


Jumlah tiket harus berupa angka bulat lebih dari 0. Jika jumlah tiket melebihi kapasitas studio, transaksi akan ditolak.

Setelah berhasil, sistem membuat kode transaksi dan menyimpan data tiket ke tabel 'tiket' melalui 'TiketDAO', serta data transaksi ke tabel 'transaksi' melalui 'TransaksiDAO'. Total pembayaran dihitung dengan diskon otomatis berdasarkan total harga dan jumlah tiket.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/8880c2a1-1d4d-4702-99e7-a3de66788cab" />

---

## 6. Riwayat Transaksi

Admin dapat melihat seluruh riwayat transaksi, sedangkan penonton hanya dapat melihat riwayat transaksi miliknya sendiri. Data diambil dari database menggunakan query 'JOIN' pada tabel 'transaksi', 'tiket', 'penonton', 'film', dan 'studio'.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/21b816d9-3e95-4348-bd21-32b4c79d5728" />

---

# Koneksi Database (JDBC)

Koneksi ke database dibuat pada class 'DatabaseConnection' yang berada di package 'config'.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/26a2c22b-9364-4e80-8294-906e3acc5371" />


Driver MySQL ditambahkan melalui dependency Maven pada 'pom.xml':

<img width="500" alt="image" src="https://github.com/user-attachments/assets/25341eae-b7c5-48cc-806a-7d127e6bd47b" />


Setiap class DAO memanggil 'DatabaseConnection.getConnection()' setiap kali membutuhkan koneksi ke database.

---

# Prepared Statement

Seluruh query pada program menggunakan 'PreparedStatement'. Nilai yang dimasukkan ke query diwakili tanda '?', lalu diisi menggunakan method seperti 'setString()', 'setInt()', dan 'setDouble()'. Dengan cara ini, query lebih aman dari **SQL Injection** karena input pengguna tidak digabungkan langsung ke dalam teks SQL.

Contoh pada 'FilmDAO' untuk menambah data (Create):

<img width="500" alt="image" src="https://github.com/user-attachments/assets/5d3cc84c-c87b-44a8-adb5-eeb5f723803f" />

Contoh untuk menampilkan data (Read):

<img width="500" alt="image" src="https://github.com/user-attachments/assets/5ee27299-5e21-4c72-8815-3dabcff29e1b" />


Penggunaan 'try-with-resources' membuat 'Connection', 'PreparedStatement', dan 'ResultSet' ditutup otomatis setelah selesai digunakan.

---

# Penerapan DAO (Data Access Object)

DAO adalah pola yang memisahkan kode akses database dari bagian program lainnya. Pada program ini, setiap tabel memiliki satu class DAO pada package 'dao':

* 'FilmDAO' : tambah, tampil, ubah, dan hapus data film
* 'StudioDAO' : tambah, tampil, ubah, dan hapus data studio
* 'PenontonDAO' : tambah, login, cari email, dan tampil data penonton
* 'TiketDAO' : tambah, tampil, ubah, dan hapus data tiket
* 'TransaksiDAO' : tambah transaksi, tampil seluruh transaksi, dan tampil transaksi berdasarkan penonton

'BioskopController' tidak menulis query SQL secara langsung, tetapi hanya memanggil method dari DAO.

Contohnya:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/bff36ea2-2109-42ba-b953-bd17e17ddd22" />


filmDAO.tambah(film);
List<Film> daftar = filmDAO.getSemua();
'''

Dengan DAO, tugas setiap bagian menjadi lebih jelas. View menangani tampilan dan input, Controller mengatur alur program, Model menyimpan data objek, dan DAO menangani akses ke database.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/5fb5fb54-f305-494d-aefa-b9a027685f23" />

---

# Dummy Data (File .sql)

Struktur tabel dan data awal disimpan dalam file 'sistem_manajemen_bioskop.sql', lalu di-import ke database MySQL (melalui phpMyAdmin pada Laragon). Data awal **diisi melalui database, bukan melalui kode program**.

Database yang digunakan bernama 'sistem_manajemen_bioskop' dengan 5 tabel:

* 'film' (id_film, Judul, genre, durasi, harga_tiket)
* 'studio' (id_studio, nama, kapasitas, tipe)
* 'penonton' (id_penonton, nama, email, no_hp, password)
* 'tiket' (id_tiket, id_penonton, id_film, id_studio, jumlah, harga_per_tiket, total_harga)
* 'transaksi' (id_transaksi, kode_transaksi, id_tiket, total_awal, diskon, total_bayar, tanggal_transaksi)

Tabel 'tiket' berelasi dengan 'penonton', 'film', dan 'studio' melalui foreign key, sedangkan tabel 'transaksi' berelasi dengan 'tiket'.

Contoh data awal pada tabel 'film':

'''sql
INSERT INTO 'film' ('id_film', 'Judul', 'genre', 'durasi', 'harga_tiket') VALUES
(1, 'Avenger: Endgame', 'Action, Sci-fi', 180, 50000),
(2, 'Interstellar', 'Sci-fi', 150, 45000),
(3, 'Pabrik Rambut', 'Thriller', 170, 50000),
(4, 'Inside Out', 'Fantasy', 200, 45000);
'''

Contoh data awal pada tabel 'studio':

'''sql
INSERT INTO 'studio' ('id_studio', 'nama', 'kapasitas', 'tipe') VALUES
(1, 'Studio 1', 100, 'Regular'),
(2, 'Studio 2', 80, 'VIP'),
(3, 'Studio 3', 120, 'Regular'),
(4, 'Studio 4', 60, 'VIP');
'''

Selain itu, file '.sql' juga berisi data awal untuk tabel 'penonton' (5 data), 'tiket' (4 data), dan 'transaksi' (4 data), sehingga fitur lihat data penonton, kelola tiket, dan riwayat transaksi juga langsung menampilkan data.

Contohnya data pada tabel 'transaksi':

'''sql
INSERT INTO 'transaksi' ('id_transaksi', 'kode_transaksi', 'id_tiket', 'total_awal', 'diskon', 'total_bayar', 'tanggal_transaksi') VALUES
(1, 'TRX001', 1, 100000, 10000, 90000, '2026-10-12 09:59:25'),
(2, 'TRX002', 2, 55000, 5000, 50000, '2026-10-12 09:59:25'),
(3, 'TRX003', 3, 100000, 10000, 90000, '2026-10-12 09:59:25'),
(4, 'TRX004', 4, 55000, 0, 55000, '2026-10-12 09:59:25');
'''

Karena data sudah ada di database, saat fitur Read dijalankan data langsung tampil tanpa perlu input dari awal.

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

---

# Penerapan Konsep OOP

## Encapsulation

Encapsulation diterapkan dengan menggunakan atribut 'private' pada class.

Contohnya pada class 'Film':

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


Atribut tersebut tidak dapat diakses secara langsung dari class lain. Untuk mengakses atau mengubah data digunakan getter dan setter.

Konsep ini juga diterapkan pada class seperti 'Pengguna', 'Admin', 'Penonton', 'Studio', 'Tiket', dan 'Transaksi'.

Encapsulation membuat data dalam objek lebih terkontrol dan tidak dapat diubah secara langsung tanpa melalui method yang disediakan.

---

## Inheritance

Inheritance diterapkan pada hubungan antara class 'Pengguna', 'Admin', dan 'Penonton'.

'Pengguna' digunakan sebagai class induk:

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


Kemudian 'Admin' dan 'Penonton' mewarisi class tersebut:

### Admin
---
<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

### Penonton
---
<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

Atribut umum seperti:

'''java
id
nama
email
noHP
'''

diletakkan pada class 'Pengguna'.

Dengan inheritance, class 'Admin' dan 'Penonton' dapat menggunakan atribut dan method yang berasal dari class 'Pengguna' tanpa perlu membuatnya kembali.

---

## 1. MVC (Model View Controller)

Program menggunakan struktur **MVC** yang membagi program menjadi tiga bagian, dengan tambahan **DAO** untuk akses database.

### Model

Model digunakan untuk menyimpan data dan objek program.

Contohnya:

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


### View

View terdapat pada:

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


View bertugas menangani tampilan program dan input dari pengguna, seperti menu, input data, serta validasi input.

### Controller

Controller terdapat pada:

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


Controller bertugas mengatur alur program dan menghubungkan View dengan Model dan DAO.

Contohnya ketika penonton membeli tiket, Controller mengambil data film dan studio melalui DAO, memproses jumlah tiket, membuat objek 'Tiket', kemudian membuat objek 'Transaksi', lalu menyimpannya ke database melalui 'TiketDAO' dan 'TransaksiDAO'.

Dengan MVC, kode menjadi lebih terstruktur karena tampilan, data, dan proses program dipisahkan.

---

## 2. Polymorphism dan Method Overriding

Polymorphism diterapkan melalui **method overriding**.

Pada class 'Pengguna' terdapat method abstract:

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


Method tersebut kemudian dioverride oleh class 'Admin':

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


Sedangkan pada class 'Penonton':

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


Nama method yang digunakan sama, yaitu 'getRole()' dan 'tampilkanMenu()', tetapi hasilnya berbeda berdasarkan class yang mengimplementasikannya.

---

## 3. Method Overloading

Method overloading adalah method dengan nama yang sama tetapi parameter yang berbeda. Pada program ini overloading diterapkan pada:

**a. Method 'hitungDiskon()' pada class 'Penonton'**

'''java
public double hitungDiskon(double total)

public double hitungDiskon(double total, int jumlahTiket)
'''

Versi pertama menghitung diskon berdasarkan total harga (5% untuk total minimal Rp50.000 dan 10% untuk total minimal Rp100.000). Versi kedua menambahkan jumlah tiket sebagai parameter, yaitu diskon 15% jika membeli minimal 4 tiket.

**b. Constructor pada class 'Film', 'Studio', 'Tiket', 'Transaksi', 'Pengguna', dan 'Penonton'**

Terdapat dua constructor: satu tanpa ID untuk data baru yang akan disimpan ke database, dan satu dengan ID untuk data yang dibaca dari database.

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

---

## 4. Interface (Abstraction)

Interface adalah sebuah struktur dalam Java yang digunakan sebagai aturan atau kontrak bagi class yang menggunakannya. Interface berisi method yang harus dibuat atau diimplementasikan oleh class tersebut.

Interface biasanya digunakan untuk menerapkan abstraction, karena interface hanya menentukan apa yang harus dilakukan, sedangkan cara melakukan proses tersebut ditentukan oleh class yang mengimplementasikannya.

Pada program ini terdapat interface bernama 'Pembayaran' yang digunakan untuk mengatur proses pembayaran. Interface tersebut memiliki dua method, yaitu:

'''java
public interface Pembayaran {

    double hitungTotal();

    void tampilkanPembayaran();
}
'''

<img width="350" alt="image" src="GANTI_LINK_GAMBAR" />

Interface tersebut diimplementasikan oleh class 'Transaksi':

'''java
public class Transaksi implements Pembayaran {

    @Override
    public double hitungTotal() {
        return totalAwal - diskon;
    }

    @Override
    public void tampilkanPembayaran() {
        // menampilkan detail pembayaran
    }
}
'''

<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

Method 'hitungTotal()' menghitung total pembayaran setelah diskon, sedangkan 'tampilkanPembayaran()' menampilkan kode transaksi, nama penonton, film, studio, jumlah tiket, harga awal, diskon, dan total bayar.

---

# Fitur Program

Program memiliki beberapa fitur utama:

* Koneksi database MySQL menggunakan JDBC
* Prepared Statement pada seluruh query
* DAO untuk setiap tabel
* Dummy data awal melalui file .sql
* Interface Pembayaran
* Login Admin
* Login Penonton
* Registrasi Penonton
* CRUD Film
* CRUD Studio
* CRUD Tiket
* Lihat Data Penonton
* Lihat Profil Penonton
* Pembelian Tiket
* Perhitungan harga tiket otomatis
* Tambahan harga VIP Rp10.000
* Diskon otomatis
* Validasi nama
* Validasi email
* Validasi password
* Validasi angka
* Validasi pilihan menu
* Validasi tipe studio
* Validasi kapasitas studio
* Riwayat transaksi

---

# Contoh Validasi

### Validasi Nama

'''text
Nama : Yahya123
'''

Output:

'''text
Nama minimal 2 karakter dan hanya berisi huruf/spasi.
'''
<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />


### Validasi Email

'''text
Email : yahyagmail.com
'''

Output:

'''text
Format email tidak valid.
'''
<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

### Validasi Password

'''text
Password : abc
'''

Output:

'''text
Password minimal 6 karakter.
'''
<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

### Validasi Harga

'''text
Harga tiket film : Rpabc
'''

Output:

'''text
Harga harus lebih dari 0.
'''
<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

### Validasi Tipe Studio

Tipe studio dipilih melalui menu angka, sehingga hanya tersedia dua pilihan:

'''text
1. Regular
2. VIP
'''

Jika memasukkan pilihan di luar menu, misalnya:

'''text
Pilih tipe studio : 3
'''

Output:

'''text
Masukkan angka antara 1 sampai 2.
'''
<img width="500" alt="image" src="GANTI_LINK_GAMBAR" />

### Validasi Kapasitas Tiket

Jika kapasitas studio adalah 100 orang:

'''text
Jumlah tiket : 109
'''

Output:

'''text
Jumlah tiket melebihi kapasitas studio.
'''
<img width="345" height="300" alt="image" src="GANTI_LINK_GAMBAR" />

maka sistem akan menolak karena jumlah tiket melebihi kapasitas studio.
