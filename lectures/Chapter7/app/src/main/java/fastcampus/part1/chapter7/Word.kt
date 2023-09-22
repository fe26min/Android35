package fastcampus.part1.chapter7

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// 데이터를 홀딩하는 클래스
@Parcelize
@Entity(tableName = "word")
data class Word(
    val text : String,
    val mean : String,
    val type : String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
) : Parcelable
