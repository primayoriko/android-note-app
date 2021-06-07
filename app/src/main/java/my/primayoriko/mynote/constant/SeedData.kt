package my.primayoriko.mynote.constant

import my.primayoriko.mynote.domain.Note

object SeedData {
    private val note1 = Note(
        "1. Bahagia",
        "Ku bahagia bersamamu",
        Note.NoteType.OTHER,
        false
    )
    private val note2 = Note(
        "2. Sedih",
        "Nangis mulu nih belakangan",
        Note.NoteType.OTHER,
        false
    )
    private val note3 = Note(
        "3. Senang",
        "Keknya beberapa hari lagi bakal seneng2 nih",
        Note.NoteType.OTHER,
        false
    )
    private val note4 = Note(
        "4. Marah",
        "Ugh.. dia nyebelin banget sich",
        Note.NoteType.OTHER,
        false
    )
    private val note5 = Note(
        "Kerjaan Besok",
        "Laporan ke si anu, masuk jam 8 pagi, beresin meja",
        Note.NoteType.WORK,
        true
    )
    private val note6 = Note(
        "Pelajaran Hari Ini",
        "belajar kotlin ehe",
        Note.NoteType.STUDY,
        true
    )
    private val note7 = Note(
        "Peer Buat Minggu Depan",
        "Duh masih ada Dicoding ML lagi",
        Note.NoteType.STUDY,
        true
    )
    private val note8 = Note(
        "5. Kecewa",
        "duh kok gagal terus ya.. padahal dah banyak mencoba",
        Note.NoteType.OTHER,
        false
    )
    private val note9 = Note(
        "Projek Bulan depan",
        "Gali kanal, bantu palestina, MERDEKA!!",
        Note.NoteType.WORK,
        true
    )
    private val note10 = Note(
        "6. Kepuasan",
        "Tapi pada akhirnya, kuyakin semua 'kan terbayarkan",
        Note.NoteType.OTHER,
        true
    )
    val seedList = listOf(
        note1,
        note2,
        note3,
        note4,
        note5,
        note6,
        note7,
        note8,
        note9,
        note10
    )
}