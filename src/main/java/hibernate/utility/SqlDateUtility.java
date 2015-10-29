package hibernate.utility;

import java.sql.Date;

/**
 * Created by Machi on 25/10/2015.
 */
public class SqlDateUtility {

    public static Date now() {
        return  new Date(System.currentTimeMillis());
    }

}
