package ir.jaShakouri.tuturial.data.remote.repo

interface BaseRepository<T> {
    fun getItems(): List<T>
}