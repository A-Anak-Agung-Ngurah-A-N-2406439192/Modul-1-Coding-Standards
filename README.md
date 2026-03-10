# Reflection 1
1. Clean Code Principles Applied<br>
   -Meaningful Names: Penamaan variabel dan method jelas (misal: delete, edit).<br>
   -Single Responsibility Principle: Pemisahan tugas yang jelas antara Controller, Service, dan Repository.<br>
   -Functions Do One Thing: Setiap fungsi hanya melakukan satu tugas spesifik.<br>

2. Secure Coding Practices Applied<br>
   -UUID Generation: Menggunakan UUID acak untuk ID produk guna mencegah serangan IDOR atau penebakan ID.<br>

3. Mistakes and Areas for Improvement<br>
   -No Error Handling: Belum ada penanganan jika ID produk tidak ditemukan (bisa menyebabkan error/halaman kosong).<br>

# Reflection 2

1. Unit Testing & Code Coverage<br>
   Bagaimana perasaan saya setelah membuat unit test?<br>
   Setelah membuat unit test, saya merasa lebih percaya diri dengan kode yang saya buat.<br>
   Hal ini meyakinkan saya bahwa logika utama dari fitur-fitur (seperti Create, Edit, dan Delete) berfungsi sebagaimana mestinya.<br>
   Selain itu, unit test mengurangi rasa takut akan merusak fungsionalitas yang sudah ada ketika saya melakukan refactoring atau menambahkan fitur baru di masa depan.<br>

Berapa banyak unit test yang harus dibuat dalam satu kelas?<br>
Tidak ada angka pasti. Jumlah unit test bergantung pada kompleksitas kelas tersebut. Idealnya, harus memiliki cukup tes untuk mencakup:<br>
- Semua method publik.<br>
- Skenario positif (happy paths).<br>
- Skenario negatif (edge cases, input tidak valid, exceptions).<br>

Bagaimana cara memastikan unit test sudah cukup?<br>
Untuk memastikan kecukupannya, bisa menggunakan alat Code Coverage untuk melihat baris kode mana yang dieksekusi selama pengujian.<br>
Namun, juga harus meninjau secara manual apakah kita sudah mencakup kasus-kasus logika yang mungkin terlewat oleh alat otomatis.<br>

Apakah 100% code coverage berarti tidak ada bug?<br>
Tidak, 100% code coverage tidak menjamin bahwa kode bebas dari bug atau error.<br>
- Hal itu hanya berarti setiap baris kode telah dieksekusi setidaknya satu kali selama pengujian.<br>
- Code coverage tidak memeriksa kebutuhan yang hilang (fitur yang lupa diimplementasikan).<br>
- Code coverage tidak menjamin kebenaran logika (misalnya, kodenya jalan, tapi rumus perhitungannya salah).<br>
- Code coverage mungkin tidak menangkap masalah concurrency atau masalah integrasi.<br>

2. Functional Testing & Clean Code
Clean code dari functional test suite yang baru:
Membuat kelas functional test baru dengan menyalin prosedur setup dan variabel instance yang persis sama dari kelas sebelumnya melibatkan banyak duplikasi kode.<br>

Masalah Clean Code yang Teridentifikasi:<br>
- Pelanggaran Prinsip DRY (Don't Repeat Yourself): Kita mengulang kode setup yang sama (inisialisasi `baseUrl`, `serverPort`, setup WebDriver) di beberapa file.
- Masalah Pemeliharaan (Maintenance Nightmare): Jika kita perlu mengubah logika setup (misalnya, mengganti driver browser atau konfigurasi URL dasar),
  kita harus memperbaruinya di setiap class tes satu per satu.<br>

Perbaikan:<br>
Untuk membuat kode lebih bersih, kita sebaiknya menggunakan teknik Inheritance atau membuat Base Test Class.<br>
- Buat class dasar (misalnya, `BaseFunctionalTest`) yang menangani setup umum:<br>
   - Mendefinisikan `@LocalServerPort`.<br>
   - Menginisialisasi `baseUrl`.<br>
   - Konfigurasi WebDriver yang umum digunakan.<br>
- Buat `CreateProductFunctionalTest` dan class tes baru lainnya mewarisi (extends) `BaseFunctionalTest` ini.<br>

# Reflection 3 (Module 2)

1. List the code quality issue(s) that you fixed during the exercise and explain your strategy
   on fixing them. <br>
- Test Coverage Tidak Lengkap (Branch Coverage): Laporan JaCoCo menunjukkan bahwa beberapa method, terutama findById<br> 
di ProductRepository, belum dites pada beberapa jalur percabangan (kondisi if/else). Strategi saya adalah membuat skenario<br>
unit test yang komprehensif untuk mencakup semua kemungkinan eksekusi kode, termasuk skenario positif (produk ditemukan), <br>
skenario negatif (produk tidak ditemukan), dan edge case (saat repositori kosong), yang akhirnya menaikkan coverage menjadi 100%.<br>
- Saat fase testing, saya menemukan peringatan bahwa anotasi org.springframework.boot.test.mock.mockito.MockBean sudah deprecated <br>
sejak Spring Boot versi 3.4.0. Strategi saya untuk memperbaikinya adalah dengan memperbarui baris import dan mengganti @MockBean dengan <br>
anotasi @MockitoBean yang lebih modern untuk memastikan kode tetap maintainable dan kompatibel dengan pembaruan Spring Boot di masa depan. <br>
<br>
2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
   implementation has met the definition of Continuous Integration and Continuous<br>
   Deployment? Explain the reasons (minimum 3 sentences)!<br>
   Ya, saya rasa implementasi saat ini sudah berhasil memenuhi definisi dari Continuous Integration (CI) dan Continuous Deployment (CD).<br>
   Untuk aspek Continuous Integration, workflow GitHub Actions yang saya buat akan secara otomatis melakukan proses build, mengeksekusi seluruh unit test <br>
dan functional test, serta menjalankan analisis kualitas kode menggunakan SonarCloud setiap kali ada kode baru yang di-push ke GitHub.<br>
   Untuk aspek Continuous Deployment, saya telah mengonfigurasi workflow GitHub Actions (cd.yml) yang terhubung ke Heroku. Artinya, begitu kode baru selesai di-push<br>
dan lolos pengecekan CI (ci.yml) dengan status success, workflow CD akan otomatis berjalan untuk mem-build dan men-deploy aplikasi ke server live Heroku.<br>

# Reflection 4 (Module 3)

1) Explain what principles you apply to your project!
   Dalam proyek ini, saya telah menerapkan kelima prinsip SOLID untuk memastikan kode lebih modular, mudah di-maintain, dan mudah dikembangkan:

SRP (Single Responsibility Principle): Saya memisahkan CarController dari ProductController.java menjadi filenya sendiri. Dengan ini, setiap kelas hanya memiliki satu tanggung jawab (mengurus routing Product atau Car saja). Selain itu, pemisahan tugas juga jelas antara Controller (routing), Service (logika bisnis), dan Repository (penyimpanan data).

OCP (Open/Closed Principle): Pada ProductRepositoryImpl dan CarRepositoryImpl, saya memodifikasi fungsi edit dan update. Alih-alih memperbarui atribut objek satu per satu (seperti setName, setQuantity), kode sekarang langsung menimpa objek di dalam List (menggunakan data.set(index, updatedObject)). Ini membuat kelas terbuka untuk ekstensi (menambah atribut baru di Model) namun tertutup untuk modifikasi (tidak perlu mengubah kode fungsi edit di Repository).

LSP (Liskov Substitution Principle): Saya menghapus extends ProductController pada CarController. CarController pada dasarnya bukanlah turunan dari ProductController, sehingga memaksakan inheritance di sini akan melanggar LSP karena subclass tidak bisa menggantikan superclass-nya secara natural.

ISP (Interface Segregation Principle): Saya memecah antarmuka (interface) yang terlalu besar ("gendut") menjadi lebih spesifik. ProductService dan ProductRepository dipecah menjadi antarmuka untuk operasi baca (Read) dan operasi tulis (Write), contohnya: ProductReadService dan ProductWriteService. Hal yang sama juga diterapkan pada entitas Car.

DIP (Dependency Inversion Principle): Modul tingkat tinggi sekarang bergantung pada abstraksi, bukan implementasi konkret.

Pada Controller, saya mengubah injeksi dari kelas konkret menjadi Interface (contoh: @Autowired private CarService alih-alih CarServiceImpl).

Pada Service, saya mengubah injeksi Repository agar menggunakan Interface (contoh: @Autowired private ProductWriteRepository), bukan menembak langsung ke kelas ProductRepositoryImpl.

2) Explain the advantages of applying SOLID principles to your project with examples.
   Menerapkan prinsip SOLID memberikan banyak keuntungan jangka panjang bagi proyek:

Kemudahan Pemeliharaan (Maintainability): Berkat penerapan OCP, jika di masa depan saya perlu menambahkan atribut carBrand atau productPrice pada model data, saya tidak perlu lagi membuka dan memodifikasi kode fungsi update di dalam kelas Repository. Kode lama aman dari risiko bug baru akibat modifikasi.

Mudah Diuji (Testability): Berkat DIP, pengujian (Unit Testing) menjadi jauh lebih mudah. Pada ProductServiceImplTest, saya bisa dengan mudah melakukan mocking pada antarmuka ProductWriteRepository tanpa perlu memikirkan bagaimana implementasi asli (seperti ProductRepositoryImpl) bekerja atau terhubung ke penyimpanan aslinya.

Modularitas dan Keamanan Kode: Dengan penerapan ISP, kelas yang membutuhkan data hanya akan diberikan akses ke metode yang relevan. Misalnya, jika suatu saat ada fitur yang tugasnya hanya untuk display katalog ke pembeli, saya cukup menginjeksi ProductReadService. Kelas tersebut tidak akan bisa (secara tidak sengaja) memanggil fungsi delete() karena metode tersebut berada di ProductWriteService.


3) Explain the disadvantages of not applying SOLID principles to your project with examples.
   Jika tidak menerapkan prinsip SOLID, proyek akan berubah menjadi Spaghetti Code yang kaku, rapuh, dan sulit diperbaiki:

Kaku Terhadap Perubahan (Rigidity): Tanpa OCP, fungsi update di Repository akan mendaftar pembaruan properti satu per satu (car.setCarColor(...), car.setCarName(...)). Setiap kali ada penambahan satu atribut baru di model, developer wajib mengingat untuk membuka Repository dan menambahkan baris kode baru. Jika terlupa, aplikasi akan mengalami bug di mana data baru tidak tersimpan saat diedit.

Ketergantungan yang Kuat (Tight Coupling): Jika mengabaikan DIP, maka kelas tingkat tinggi akan sangat bergantung pada detail low-level. Misalnya, jika CarServiceImpl bergantung langsung pada CarRepositoryImpl (yang menyimpannya di memori/List), maka saat proyek ingin bermigrasi menggunakan Database (misal PostgreSQL), saya harus membongkar dan menulis ulang banyak kode di dalam CarServiceImpl.

Polusi Antarmuka (Interface Pollution): Tanpa memedulikan ISP dan SRP, kita akan menumpuk semua kode di satu tempat (seperti menggabungkan semua urusan mobil di dalam ProductController). Ini membuat file menjadi sangat panjang (ribuan baris), pengerjaan secara tim (kolaborasi Git) akan sangat rentan terkena merge conflict, dan kode sulit dipahami oleh developer baru.

# Reflection 5 (Module 4)

**1. Refleksi berdasarkan pertanyaan reflektif Percival (2017) terkait alur TDD:**
Berdasarkan pertanyaan refleksi diri yang diajukan oleh Percival, alur TDD ini terbukti sangat berguna bagi proses pengembangan saya. Menulis *test* terlebih dahulu memaksa saya untuk berpikir dari sudut pandang pengguna dan mempertimbangkan dengan cermat ekspektasi perilaku program serta *edge cases* sebelum menulis implementasi kodenya. Alur ini memberikan *feedback* instan, sehingga saya lebih yakin bahwa fitur yang dibuat berfungsi dengan benar dan membantu saya mendeteksi *bug* logika sejak awal. Selain itu, *test* ini juga berfungsi sebagai *executable documentation* yang sangat membantu.

Jika ada hal yang perlu ditingkatkan untuk pembuatan *test* berikutnya, saya harus meluangkan lebih banyak waktu untuk menganalisis nilai batas (*boundary values*) dan menulis struktur *test* yang lebih bersih. Hal ini penting agar *test* yang dibuat tidak menjadi beban *maintenance* ketika ada perubahan kebutuhan bisnis di masa mendatang.

**2. Refleksi penerapan prinsip F.I.R.S.T. pada Unit Test di Tutorial:**
Saya merasa *unit test* yang telah saya buat di tutorial sudah berhasil mengikuti prinsip F.I.R.S.T. dengan baik:
* **Fast (Cepat):** *Test* berjalan dengan sangat cepat karena terisolasi dengan baik dan menggunakan *mocking* untuk *dependencies*, bukan bergantung pada *database* asli atau koneksi jaringan.
* **Independent (Mandiri):** Setiap *unit test* berdiri sendiri. Dengan memanfaatkan anotasi *setup* seperti `@BeforeEach` pada JUnit, *test* tidak saling bergantung pada *state* atau urutan eksekusi *test* lainnya.
* **Repeatable (Dapat Diulang):** *Test* memberikan hasil yang deterministik dan konsisten meskipun dijalankan berulang kali di berbagai *environment* yang berbeda.
* **Self-validating (Memvalidasi Diri Sendiri):** Setiap *test* memiliki *assertions* yang jelas. Hasil *pass* atau *fail* ditentukan secara otomatis oleh sistem tanpa perlu mengecek *output* konsol secara manual.
* **Timely (Tepat Waktu):** *Test* ditulis tepat sebelum kode produksi dibuat, secara ketat mematuhi siklus *Red-Green-Refactor* dalam TDD.

Untuk pembuatan *test* ke depannya, hal utama yang ingin saya tingkatkan adalah cakupan *test cases*. Saya berencana untuk lebih memperbanyak *negative test cases* dan pengujian batas (*boundary testing*) agar aplikasi lebih tangguh dalam menangani *input* pengguna yang tidak sesuai atau tidak terduga.