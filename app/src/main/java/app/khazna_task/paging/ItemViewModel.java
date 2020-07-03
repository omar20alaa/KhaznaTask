package app.khazna_task.paging;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import com.app.khazna_task.model.Posts;
import com.app.khazna_task.paging.ItemDataSource;
import com.app.khazna_task.repository.PostsRespository;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Posts>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Posts>> liveDataSource;
    private PostsRespository postsRespository;

    public ItemViewModel(Application application) {

        postsRespository = new PostsRespository(application);
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(postsRespository);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        postsRespository = new PostsRespository(application);

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }
}
