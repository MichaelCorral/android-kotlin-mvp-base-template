package com.michaelcorral.mvptemplate.data.itunes.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.michaelcorral.mvptemplate.data.itunes.local.ItunesContent
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ItunesContentDao {

    @Insert
    fun insertItunesContent(itunesContent: ItunesContent): Single<Long>

    @Query("SELECT * FROM ItunesContent")
    fun queryItunesContent(): Single<ItunesContent>

    @Query("DELETE FROM ItunesContent")
    fun deleteAllItunesContent(): Completable
}

/* Notes:
    @Insert
    fun insertWithCompletable(timeEntry: TimeEntry): Completable  //onComplete is called as soon as the insertion was done

    @Insert
    fun insertWithSingle(timeEntry: TimeEntry): Single<Long>  // the value emitted on onSuccess is the row id of the item inserted

    @Insert
    fun insertWithMaybe(timeEntry: TimeEntry): Maybe<Long>  //the value emitted on onSuccess is the row id of the item inserted

    @Insert
    fun insertMultipleWithSingle(timeEntries: MutableList<TimeEntry>): Single<List<Long>>  //  the value emitted on onSuccess is the list of row ids of the items inserted

    @Insert
    fun insetMultipleWithMaybe(timeEntries: MutableList<TimeEntry>): Maybe<List<Long>>  // the value emitted on onSuccess is the list of row ids of the items inserted

    @Update
    fun updateWithCompletable(timeEntry: TimeEntry): Completable  // onComplete is called as soon as the update/delete was done

    @Update
    fun updateWithSingle(timeEntry: TimeEntry): Single<Int>  //  the value emitted on onSuccess is the number of rows affected by update

    @Update
    fun updateAllWithSingle(timeEntries: MutableList<TimeEntry>): Single<Int>  //  the value emitted on onSuccess is the number of rows affected by update

    @Update
    fun updateWithMaybe(timeEntry: TimeEntry): Maybe<Int>  //  the value emitted on onSuccess is the number of rows affected by update

    @Delete
    fun deleteWithCompletable(timeEntry: TimeEntry): Completable  // onComplete is called as soon as the delete was done

    @Delete
    fun deleteAllWithSingle(timeEntries: MutableList<TimeEntry>): Single<Int>  //the value emitted on onSuccess is the number of rows affected by delete

    @Delete
    fun deleteWithSingle(timeEntry: TimeEntry): Single<Int>  // the value emitted on onSuccess is the number of rows affected by update

    @Delete
    fun deleteWithMaybe(timeEntry: TimeEntry): Maybe<Int>  //  the value emitted on onSuccess is the number of rows affected by update

    @Query("SELECT * FROM TimeEntry WHERE id = :id")
    fun queryWithMaybe(id: String): Maybe<TimeEntry>  // When there is no user in the database and the query returns no rows, Maybe will complete.

    @Query("SELECT * FROM TimeEntry WHERE id = :id")
    fun queryWithSingle(id: String): Single<TimeEntry>  // When there is no user in the database and the query returns no rows, Single will trigger onError(EmptyResultSetException.class)

    //When there is no user in the database and the query returns no rows, the Flowable will not emit, neither onNext, nor onError.
    //When there is a user in the database, the Flowable will trigger onNext.
    //Every time the user data is updated, the Flowable object will emit automatically, allowing you to update the UI based on the latest data.
    @Query("SELECT * FROM TimeEntry WHERE id = :id")
    fun queryWithFlowable(id: String): Flowable<TimeEntry>
 */