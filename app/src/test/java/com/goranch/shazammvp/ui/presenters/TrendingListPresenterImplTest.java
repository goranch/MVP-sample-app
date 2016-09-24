package com.goranch.shazammvp.ui.presenters;

import com.goranch.shazammvp.api.ApiService;
import com.goranch.shazammvp.api.model.shazam.Item;
import com.goranch.shazammvp.ui.shazam.ShazamFragment;
import com.goranch.shazammvp.ui.shazam.ShazamShazamDataRepositoryImpl;
import com.goranch.shazammvp.ui.shazam.TrendingListPresenterImpl;
import com.goranch.shazammvp.ui.shazam.TrendingPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Goran Ch on 17/04/16.
 */
public class TrendingListPresenterImplTest {

    @Mock
    private ShazamFragment mockMainFragmentView;

    private ApiService mockApiService;
    private ShazamShazamDataRepositoryImpl dataRepository;
    private TrendingPresenter presenter;

    @Mock
    private ShazamShazamDataRepositoryImpl mockDateRepo;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockApiService = mock(ApiService.class);

        dataRepository = new ShazamShazamDataRepositoryImpl(mockApiService);

        presenter = new TrendingListPresenterImpl(mockMainFragmentView, mockDateRepo);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * When we have a non empty array of {@link Item} do not fetch data
     *
     * @throws Exception
     */
    @Test
    public void testLoadData() throws Exception {

        Item i = new Item();

        ArrayList<Item> items = new ArrayList<>();

        items.add(i);

        presenter.loadData(items);

        verify(mockMainFragmentView, times(0)).showLoadingProgress();

    }

    @Test
    public void testOnItemClicked() throws Exception {

    }

    /**
     * Try to make API request when calling {@link TrendingPresenter#loadData(ArrayList)} with na empty array
     * @throws Exception
     */
    @Test
    public void testRefreshData() throws Exception {

        ArrayList<Item> items = new ArrayList<>();

        Mockito.doNothing().when(mockDateRepo).loadCharts(anyObject());

        presenter.loadData(items);

        verify(mockDateRepo, times(1)).loadCharts(anyObject());
    }

    @Test
    public void testOnActivityCreated() throws Exception {

    }

    @Test
    public void testShowError() throws Exception {

    }
}