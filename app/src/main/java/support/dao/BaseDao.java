package support.dao;

import android.content.Context;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import support.util.RandomUtil;

/**
 * Created by arysuryawan on 6/26/16.
 * <p>
 * Setup App for DB Phone with using Realm lib
 */
public class BaseDao {
    protected static Realm realm;

    protected BaseDao(Context context) {
        setupRealm(context);
    }

    protected Realm setupRealm(Context context) {
        // initialize Realm
        Realm.init(context);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("newsped.realm")
                .encryptionKey(RandomUtil.encryptedKey64())
                .build();

        Realm.setDefaultConfiguration(realmConfig);
        // Get a Realm instance for this thread
        realm = Realm.getInstance(realmConfig);

        return realm;
    }


    protected static RealmObject saveToRealm(RealmObject object) {
        realm.beginTransaction();
        RealmObject obj = realm.copyToRealm(object);
        realm.commitTransaction();


        return obj;
    }

    protected static RealmResults getSearchResultRealm(Class clazz, String keyword) {
        RealmResults results = realm.where(clazz).like("title", "*" + keyword + "*", Case.INSENSITIVE).findAll();

        return results;
    }

    protected static RealmResults getObjectsRealm(Class clazz) {
        RealmResults results = realm.where(clazz).findAll();

        return results;
    }

    protected static RealmResults getObjectByKeyRealm(String key, String value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .equalTo(key, value)
                .endGroup()
                .findAll();

        return items;
    }

    protected static void closeRealm() {
        if (realm != null)
            realm.close();
    }

    protected static void deleteRealm(Class clazz) {
        try {
            realm.beginTransaction();
            realm.delete(clazz);
            realm.commitTransaction();
        } catch (Exception e) {
        }
    }


}
