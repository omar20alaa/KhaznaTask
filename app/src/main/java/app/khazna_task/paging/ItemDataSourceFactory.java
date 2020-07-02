package app.khazna_task.paging;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.app.khazna_task.model.Posts;
import com.app.khazna_task.paging.ItemDataSource;
import com.app.khazna_task.repository.PostsRespository;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Posts>> itemLiveDataSource = new MutableLiveData<>();
    private PostsRespository postsRespository;


    public ItemDataSourceFactory(PostsRespository postsRespository) {
        this.postsRespository = postsRespository;
    }

    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource(postsRespository);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Posts>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
