# Reflection 1
1. Clean Code Principles Applied<br>
   -Meaningful Names: Penamaan variabel dan method jelas (misal: delete, edit).<br>
   -Single Responsibility Principle: Pemisahan tugas yang jelas antara Controller, Service, dan Repository.<br>
   -Functions Do One Thing: Setiap fungsi hanya melakukan satu tugas spesifik.<br>

2. Secure Coding Practices Applied<br>
   -UUID Generation: Menggunakan UUID acak untuk ID produk guna mencegah serangan IDOR atau penebakan ID.<br>

3. Mistakes and Areas for Improvement<br>
   -No Error Handling: Belum ada penanganan jika ID produk tidak ditemukan (bisa menyebabkan error/halaman kosong).<br>