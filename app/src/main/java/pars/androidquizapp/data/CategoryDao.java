package pars.androidquizapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface CategoryDao {

    @Insert(onConflict = REPLACE)
    long insert(Category category);

    @Query("SELECT * FROM categories_table")
    List<Category> getAllCategories();

    /*@Query("SELECT * FROM categories_table WHERE id = :id")
    Category updateCategory(long id);*/

    //Get all the items in categories_table
    //Gets the Category with id == id..
    //Modifies the fields you've specified.
    @Query("UPDATE categories_table SET category = :category WHERE id = :id")
    void updateCategory(long id, String category);

    @Update
    int updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

}
