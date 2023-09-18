package fastcampus.part1.chapter7

import androidx.room.Entity
import androidx.room.PrimaryKey

// 데이터를 홀딩하는 클래스
@Entity(tableName = "word")
data class Word(
    val text : String,
    val mean : String,
    val type : String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
