package eu.laramartin.booklisting;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miquel Beltran on 11/18/16
 * More on http://beltran.work
 */
@Module
public class BooksModule {
    @Provides
    public BooksInteractor providesBooksInteractor() {
        return new BooksInteractorImpl();
    }
}
