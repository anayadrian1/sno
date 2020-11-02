package edu.cnm.deepdive.sno.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.sno.model.entity.FavoriteMountain;
import edu.cnm.deepdive.sno.model.entity.Trip;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface TripDao {

  @Insert
  Single<Long> insert(Trip trip);

  @Insert
  Single<List<Long>> insert(Trip... trips);

  @Insert
  Single<List<Long>> insert(Collection<Trip> trips);

  @Update
  Single<Integer> update(Trip trip);

  @Update
  Single<Integer> update(Trip... trips);

  @Update
  Single<Integer> update(Collection<Trip> trips);

  @Delete
  Single<Integer> delete(Trip trip);

  @Delete
  Single<Integer> delete(Trip... trips);

  @Delete
  Single<Integer> delete(Collection<Trip> trips);

  @Query("SELECT * FROM `Trip` ORDER BY start_time, end_time DESC")
  LiveData<List<Trip>> selectDistance();
}
