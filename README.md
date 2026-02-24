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

# Reflection 3

1. List the code quality issue(s) that you fixed during the exercise and explain your strategy
   on fixing them. <br>
   - Test Coverage Tidak Lengkap (Branch Coverage): Laporan JaCoCo menunjukkan bahwa beberapa method, terutama findById<br> 
di ProductRepository, belum dites pada beberapa jalur percabangan (kondisi if/else). Strategi saya adalah membuat skenario<br>
unit test yang komprehensif untuk mencakup semua kemungkinan eksekusi kode, termasuk skenario positif (produk ditemukan), <br>
skenario negatif (produk tidak ditemukan), dan edge case (saat repositori kosong), yang akhirnya menaikkan coverage menjadi 100%.<br>
<br>
- Saat fase testing, saya menemukan peringatan bahwa anotasi org.springframework.boot.test.mock.mockito.MockBean sudah deprecated <br>
sejak Spring Boot versi 3.4.0. Strategi saya untuk memperbaikinya adalah dengan memperbarui baris import dan mengganti @MockBean dengan <br>
anotasi @MockitoBean yang lebih modern untuk memastikan kode tetap maintainable dan kompatibel dengan pembaruan Spring Boot di masa depan. <br>

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
   implementation has met the definition of Continuous Integration and Continuous<br>
   Deployment? Explain the reasons (minimum 3 sentences)!<br>
   Ya, saya rasa implementasi saat ini sudah berhasil memenuhi definisi dari Continuous Integration (CI) dan Continuous Deployment (CD).<br>
   Untuk aspek Continuous Integration, workflow GitHub Actions yang saya buat akan secara otomatis melakukan proses build, mengeksekusi seluruh unit test <br>
dan functional test, serta menjalankan analisis kualitas kode menggunakan SonarCloud setiap kali ada kode baru yang di-push ke GitHub.<br>
   Untuk aspek Continuous Deployment, saya telah mengonfigurasi workflow GitHub Actions (cd.yml) yang terhubung ke Heroku. Artinya, begitu kode baru selesai di-push<br>
dan lolos pengecekan CI (ci.yml) dengan status success, workflow CD akan otomatis berjalan untuk mem-build dan men-deploy aplikasi ke server live Heroku.<br>
