# Tugas Kecil 3 IF2211 Strategi Algoritma Semester II tahun 2023/2024
## Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*

# AUTHOR
- NAMA  : Muhammad Gilang Ramadhan 
- NIM   : 13520137
- KELAS : K-01

## Struktur Repository :
1. **doc** -> lokasi penyimpanan laporan tugas kecil.
2. **src** -> lokasi penyimpanan *source code* dari program.
3. **test** -> lokasi penyimpanan dokumen uji.
4. **bin** -> lokasi penyimpanan executable

## Requirement Program :
- Java (Running)
- Javac (Compiler)
- Javax (GUI)

## Cara menjalankan program dengan melakukan commpile
1. Buka terminal, arahkan ke direktori tempat program disimpan yaitu pada folder src
2. Jalankan perintah berikut untuk melakukan compile
```
Javac Main.java
```
3. Jalankan perintah berikut untuk menjalankan file hasil compile
```
Java Main
```

## Cara menjalankan program dengan file executable
1. Jalankan perintah berikut untuk melakukan compile
```
./compile.sh
```
2. Jalankan perintah berikut untuk menjalankan file hasil compile
```
./run.sh
```

## Interaksi Command Line:
Setelah itu program berhasil dijalankan:
1. Input start word and end word.
2. Chose Algoritma yang akan dipakai.
3. Setelah itu tekan tombol solve untuk mendapatkan hasil program.
4. Akan diperoleh visualisasi path yang berisi rangkaian kata dari start word sampai dengan end word melalui pop up.
5. Setelah pop up diclose, maka akan diperoleh informasi banyaknya node yang dikunjungi, panjang path, dan time execution program.
6. Jika ingin melalukan input kembali, lakukan saja tanpa harus mengkompilasi dan merunning ulang program.

## Notes:
1. Dictionary yang dipakai dapat diganti melalui file dictionary.txt yang ada di folder test.
2. Daftar test case yang telah dicoba dapat diperoleh melalui folder test pada nama file input{nomor input}.txt.

## Format Masukkan File txt
1. Panjang Start word harus sama dengan panjang End Word (Jika tidak, akan divalidasi)
2. Start word atau end word tidak boleh kosong (Jika tidak, akan divalidasi)