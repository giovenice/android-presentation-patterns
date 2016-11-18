package eu.laramartin.booklisting;

import dagger.Component;

/**
 * Created by Miquel Beltran on 11/18/16
 * More on http://beltran.work
 */
@Component(dependencies = {BooksModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
