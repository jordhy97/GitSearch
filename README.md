# GitSearch
Aplikasi untuk mencari pengguna GitHub, dibuat dalam bahasa Java.

Aplikasi ini menggunakan GitHub API yang diakses secara REST.

## JSON, REST, dan GitHub API
1. JSON

    JSON (*JavaScript Object Notation*) adalah format pertukaran data yang mudah dibaca/di-*parse* dan ditulis/dibuat baik oleh manusia maupun oleh komputer. JSON menggunakan format teks yang tidak bergantung pada bahasa pemrograman apapun, tetapi menggunakan konvensi yang umumnya digunakan pada keluarga bahasa pemrograman C (C, C++, Java, JavaScript, Perl, Python, dll.). Sifat JSON yang independen terhadap bahasa ini yang membuatnya menjadi format pertukaran data yang ideal. JSON terbuat dari dua struktur yaitu:

    a.) Sekumpulan pasangan nama/nilai yang dalam beberapa bahasa pemrograman dinyatakan sebagai objek, *record*, *struct*, kamus, tabel hash, *keyed list*, atau *associative array*.

    b.) Daftar nilai yang terurut yang dapat dinyatakan sebagai *array*, vektor, atau *list*.

    Format data JSON menggunakan bentuk sebagai berikut.
   
    a.) Objek (sekumpulan nama/nilai yang tidak terurut). Objek diawali dengan { (kurawal buka) dan diakhiri dengan } (kurawal tutup). Setiap nama diikuti tanda : (semicolon) kemudian nilainya dan antar pasangan nama/nilai dipisahkan dengan , (koma).

    b.) *Array* (kumpulan nilai terurut). *Array* diawali dengan [ (kurung siku buka) dan diakhiri dengan ] (kurung siku tutup). Antara nilai yang satu dengan yang lain dipisahkan dengan , (koma).

    c.) Nilai. Nilai dapat berupa string (menggunakan tanda kutip dua), bilangan, nilai kebenaran (*true* atau *false* atau *null*), objek maupun *array*.

2. REST
  
    REST (*Representational State Transfer*) adalah standar arsitektur komunikasi berbasis web yang umumnya menggunakan protokol HTTP untuk melakukan pertukaran data. Pada REST, REST server menyediakan akses ke *resources* (data) yang kemudian akan akan diakses oleh REST client untuk digunakan. Setiap *resource* diidentifikasi oleh URI (*Universal Resource Identifiers*) dan direpresentasikan dalam bentuk format teks, JSON, atau XML. Saat REST client melakukan *request*, metode yang umumnya digunakan adalah metode yang terdapat pada HTTP yang diantaranya adalah GET (*read only resource*), PUT (membuat *resource* baru), DELETE (menghapus *resource*), POST (meng-*update resource*), dan OPTIONS (melakukan operasi yang didukung oleh *resource*). 
  
    *Web services* yang berbasis arsitektur REST disebut sebagai RESTful *web services*. Cara kerja *web service* ini adalah client melakukan sebuah *request* melalui HTTP Request kemudian server merespon melalui HTTP Response. HTTP Request terdiri dari metode yang digunakan untuk melakukan *request*, versi HTTP yang digunakan, metadata untuk HTTP Request (*Request Header*), dan konten dari *request* (*Request Body*). Sedangkan HTTP Response terdiri dari status server(*Response Code*), versi HTTP yang digunakan, metadata untuk HTTP Response (*Response Header*), dan konten dari data yang diberikan (*Response Body*).
  
    Pada arsitektur REST, status dari client tidak disimpan di server. Batasan ini disebut sebagai *Statelessness*. Dengan batasan ini, *web services* dapat melayani masing-masing *request* secara independen, dan tidak perlu menjaga state dari pengguna yang membuat desain aplikasi menjadi lebih sederhana serta meningkatkan performansi dari aplikasi.

3. GitHub API
    
	GitHub API adalah API (*Application Program Interface*) yang disediakan oleh GitHub untuk mengakses *resources* (data) yang terdapat pada GitHub. GitHub API dapat diakses dari https://api.github.com dan pengaksesannya dilakukan secara REST. Semua data diterima dan dikirim dalam format JSON. Ketika pengambilan data dilakukan pada sebuah *resource* tertentu, data akan dikirimkan dalam representasi detil (semua atribut diberikan) dan ketika data yang diambil berupa kumpulan *resources*, data akan dikirimkan dalam representasi yang berupa ringkasan (tidak semua atribut diberikan) karena akan membutuhkan waktu lebih lama untuk mengambil detil seluruh *resources* yang ingin diambil. 
	
	Pada aplikasi ini, API yang akan digunakan adalah GitHub Search API. GitHub Search API digunakan untuk melakukan pencarian spesiik terhadap suatu item berdasarkan query yang dimasukkan, misalnya pengguna, file dalam repository, dll. Untuk setiap pencarian hasil yang didapatkan dari GitHub Search API jumlahnya maksimal adalah 1000 dan diurutkan berdasarkan hasil yang paling cocok (kecuali dispesifikasikan pada parameter query). Untuk *requests* yang telah terautentikasi dapat dilakukan sebanyak 30 *requests* tiap menitnya dan untuk yang tidak terautentikasi maksimalnya adalah 10 *requests* per menit.  
	
	Detil lengkap mengenai GitHub API dapat dilihat di https://developer.github.com/v3/ dan untuk GitHub Search API dapat dilihat di https://developer.github.com/v3/search/.
