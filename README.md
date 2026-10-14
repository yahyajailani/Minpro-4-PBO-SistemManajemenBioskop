### Nama : Yahya Jailani
### NIM : 2509116085
### Kelas : C
    
 # Sistem Manajemen Bioskop

## Deskripsi Program

**Sistem Manajemen Bioskop** merupakan program berbasis Java yang digunakan untuk mengelola data film, studio, tiket, penonton, dan transaksi pembelian tiket.

Program memiliki dua jenis pengguna, yaitu **Admin** dan **Penonton**. Admin dapat mengelola data film, studio, tiket, dan penonton melalui fitur CRUD. Admin juga dapat melihat riwayat transaksi.

Penonton dapat melakukan registrasi dan login, melihat profil, melihat daftar film dan studio, membeli tiket, serta melihat riwayat transaksi.

Program juga dilengkapi dengan validasi input agar data yang dimasukkan sesuai dengan ketentuan sistem. Contohnya validasi nama, email, nomor HP, password, angka, pilihan menu, tipe studio, dan kapasitas studio.

Harga tiket ditentukan berdasarkan harga yang terdapat pada data film. Penonton tidak dapat memasukkan harga sendiri. Jika memilih studio bertipe **VIP**, sistem otomatis menambahkan biaya sebesar **Rp10.000**.

---

## Teknologi yang Digunakan

* Java
* Apache NetBeans
* Maven
* ArrayList
* Object-Oriented Programming (OOP)
* MVC (Model-View-Controller)
* Interaface
* Laragon

---

## Struktur Project

```text
src/main/java/com/mycompany/pertemuan1pbo/
│
├── Main.java
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
│   └── Transaksi.java
│   └── Pembayaran.java (Interface)
│
└── view/
    └── BioskopView.java
```
<img width="500" alt="image" src="https://github.com/user-attachments/assets/791d75a5-9727-44f8-88cd-3f317d6ff112" />

Struktur tersebut membagi program menjadi bagian **Model, View, dan Controller** sehingga setiap bagian memiliki fungsi masing-masing.

---

# Alur Program

## 1. Menu Utama

Saat program dijalankan, pengguna akan melihat menu:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/82ba7e53-211f-4436-9f46-4784da969d3b" />


Pengguna dapat memilih sesuai dengan kebutuhan.

---

## 2. Login Admin

Admin dapat login menggunakan akun bawaan:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/d9670ba6-af2e-46fe-bd2a-598290efe0cf" />


Jika login berhasil, Admin masuk ke menu Admin.

<img width="500" alt="image" src="https://github.com/user-attachments/assets/efdae180-cc28-4bec-8c34-646545513fcd" />


Admin dapat melakukan CRUD pada data yang tersedia.

---

## 3. Registrasi Penonton

Penonton yang belum mempunyai akun dapat melakukan registrasi dengan mengisi:

* Nama
* Email
* Password

Setiap data akan divalidasi sebelum disimpan.

Contohnya:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/61b56af0-383f-45c7-89bb-c22e6d8e69d6" />


akan ditolak karena nama hanya boleh berisi huruf dan spasi.

Email juga diperiksa agar mengikuti format yang sesuai.

---

## 4. Login Penonton

Setelah melakukan registrasi, penonton dapat login menggunakan email dan password.

Jika berhasil, penonton akan masuk ke menu:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/9984cbd5-10f5-492a-ba6d-d1db1e7c6128" />

---

## 5. Pembelian Tiket

Pada proses pembelian tiket, penonton memilih:

1. Film
2. Studio
3. Jumlah tiket

Harga tiket tidak dimasukkan oleh penonton karena sudah berasal dari data film.

Contohnya:

```text
Harga Film = Rp50.000
```

Jika memilih:

```text
Studio Regular
```

maka harga tiket:

```text
Rp50.000
```
<img width="500" alt="image" src="https://github.com/user-attachments/assets/90bd4837-b94b-466f-9b7d-a17830f2593d" />


Sedangkan jika memilih:

```text
Studio VIP
```

maka sistem otomatis menghitung:

```text
Rp50.000 + Rp10.000 = Rp60.000
```
<img width="500" alt="image" src="https://github.com/user-attachments/assets/012189ad-88b9-4c21-a1c4-c81a0c75e426" />


Jika jumlah tiket melebihi kapasitas studio, transaksi akan ditolak.

Setelah berhasil, sistem membuat ID transaksi dan menyimpan data transaksi ke dalam 'ArrayList'.

---

# Penerapan Konsep OOP

## Encapsulation

Encapsulation diterapkan dengan menggunakan atribut `private` pada class.

Contohnya pada class `Film`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/b971e4c1-10a8-4fcd-85e2-0eb9be7c5389" />


Atribut tersebut tidak dapat diakses secara langsung dari class lain. Untuk mengakses atau mengubah data digunakan getter dan setter.

Contohnya:



Konsep ini juga diterapkan pada class seperti `Pengguna`, `Admin`, `Penonton`, `Studio`, `Tiket`, dan `Transaksi`.

Encapsulation membuat data dalam objek lebih terkontrol dan tidak dapat diubah secara langsung tanpa melalui method yang disediakan.

---

## Inheritance

Inheritance diterapkan pada hubungan antara class `Pengguna`, `Admin`, dan `Penonton`.

`Pengguna` digunakan sebagai class induk:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/ab620993-dddd-4a71-8dea-399b89ec333c" />


Kemudian `Admin` dan `Penonton` mewarisi class tersebut:

### Admin
---
<img width="500" alt="image" src="https://github.com/user-attachments/assets/daeb3c55-c3ae-4892-8977-f8148dd51217" />

### Penonton
---
<img width="500" alt="image" src="https://github.com/user-attachments/assets/34477df9-a693-4087-b727-e62dfbe0ea4a" />

Atribut umum seperti:

```java
nama
email
```

diletakkan pada class `Pengguna`.

Dengan inheritance, class `Admin` dan `Penonton` dapat menggunakan atribut dan method yang berasal dari class `Pengguna` tanpa perlu membuatnya kembali.

---

## 1. MVC (Model View Controller)

Program menggunakan struktur **MVC** yang membagi program menjadi tiga bagian.

### Model

Model digunakan untuk menyimpan data dan objek program.

Contohnya:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/4d1d7499-281d-4a93-96c3-8369bb746560" />


### View

View terdapat pada:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/4effdbcc-981d-4de6-a68a-5d1c33b0b846" />


View bertugas menangani tampilan program dan input dari pengguna, seperti menu, input data, serta validasi input.

### Controller

Controller terdapat pada:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/04207fba-0ee5-468d-8b9c-a9d636bbb0d3" />


Controller bertugas mengatur alur program dan menghubungkan View dengan Model.

Contohnya ketika penonton membeli tiket, Controller mengambil data film dan studio, memproses jumlah tiket, membuat objek `Tiket`, kemudian membuat objek `Transaksi`.

Dengan MVC, kode menjadi lebih terstruktur karena tampilan, data, dan proses program dipisahkan.

---

## 2. Polymorphism dan Method Overriding

Polymorphism diterapkan melalui **method overriding**.

Pada class `Pengguna` terdapat method:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/23da0de7-6927-49d4-bf0c-725ba3cceab1" />


Method tersebut kemudian dioverride oleh class `Admin`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/2441f08c-addc-4563-8b70-d9975e88ca37" />


Sedangkan pada class `Penonton`:

<img width="500" alt="image" src="https://github.com/user-attachments/assets/95921afd-4487-4202-8b73-a6754e7cdabd" />


Nama method yang digunakan sama, yaitu `getRole()`, tetapi hasilnya berbeda berdasarkan class yang mengimplementasikannya.

Method `toString()` juga dioverride pada beberapa class seperti `Admin`, `Penonton`, `Film`, `Studio`, `Tiket`, dan `Transaksi`.

---

## 3. Method Overloading

Pada program terdapat beberapa method yang menangani input berdasarkan jenis datanya, seperti:

```java
bacaString()
bacaInt()
bacaDouble()
```

Method tersebut digunakan untuk menangani kebutuhan input yang berbeda.

Namun, secara aturan Java, method-method tersebut **belum termasuk method overloading** karena nama method-nya berbeda. Method overloading harus memiliki nama method yang sama tetapi parameter yang berbeda.

Jadi, penerapan yang benar-benar terlihat pada program adalah **method overriding**, sedangkan method overloading belum menjadi bagian utama dari kode program.

---

# Fitur Program

Program memiliki beberapa fitur utama:

* Login Admin
* Login Penonton
* Registrasi Penonton
* CRUD Film
* CRUD Studio
* CRUD Tiket
* CRUD Penonton
* Lihat Profil Penonton
* Pembelian Tiket
* Perhitungan harga tiket otomatis
* Tambahan harga VIP Rp10.000
* Validasi nama
* Validasi email
* Validasi password
* Validasi angka
* Validasi pilihan menu
* Validasi tipe studio
* Validasi kapasitas studio
* Riwayat transaksi
* ArrayList untuk penyimpanan data

---

# Contoh Validasi

### Validasi Nama

```text
Nama : Yahya Jailani
```

Output:

```text
Nama hanya boleh berisi huruf dan spasi.
```
<img width="500" alt="image" src="https://github.com/user-attachments/assets/c52c1094-581c-46a5-88ef-36c35bbd1cd6" />


### Validasi Email

```text
Email : yahyagmail.com
```

Output:

```text
Format email tidak valid.
Contoh : nama@gmail.com
```
<img width="500" alt="image" src="https://github.com/user-attachments/assets/78c25e7c-f94a-4338-94a9-aabe6328b570" />

### Validasi Harga

```text
Harga tiket film : Rpabc
```

Output:

```text
Harga harus berupa angka.
```
<img width="500" alt="image" src="https://github.com/user-attachments/assets/6073d178-ae52-40f6-bd59-92df9049dd92" />

### Validasi Tipe Studio

```text
Tipe : Gold
```

Output:

```text
Tipe studio hanya Regular atau VIP.
```

Tipe studio yang diperbolehkan:

```text
Regular
VIP
```
<img width="500" alt="image" src="https://github.com/user-attachments/assets/f7fbba15-8654-43ac-bd89-9b7b746c6a2f" />

### Validasi Kapasitas Tiket

Jika kapasitas studio adalah 100 orang:

```text
Jumlah tiket : 109
```
<img width="345" height="300" alt="Screenshot 2026-10-08 210008" src="https://github.com/user-attachments/assets/1685d785-1924-48d4-a5e2-d7cd135a3e9f" />

maka sistem akan menolak karena jumlah tiket melebihi kapasitas studio.

# Nilai Tambah
### <b>Penerapan Interface</b>
Interface adalah sebuah struktur dalam Java yang digunakan sebagai aturan atau kontrak bagi class yang menggunakannya. Interface berisi method yang harus dibuat atau diimplementasikan oleh class tersebut.
Interface biasanya digunakan untuk menerapkan abstraction, karena interface hanya menentukan apa yang harus dilakukan, sedangkan cara melakukan proses tersebut ditentukan oleh class yang mengimplementasikannya.
</p>

Pada program ini saya membuat interface bernama Pembayaran yang digunakan untuk mengatur proses pembayaran. Interface tersebut memiliki dua method, yaitu :
hitungTotal() dan
tampilkanPembayaran()

<img width="350" height="300" alt="Screenshot 2026-10-08 203704" src="https://github.com/user-attachments/assets/28837722-0db7-41d4-981b-68e3a1186ff0" />
</p>
Berikut Interface yang saya terapkan pada class pembayaran
