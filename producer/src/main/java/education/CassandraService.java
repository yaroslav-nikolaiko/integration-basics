package education;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Created by yaroslav on 09.01.17.
 */
@Service
public class CassandraService {
    @Autowired
    Session session;

    public void simpleSelect(){
        //Select query =  QueryBuilder.select().from("keywords_table");
        ResultSet resultSet = session.execute("select * from keywords_table");
        for (Row row : resultSet) {
            String kw = row.getString("kw");
            String acc_id = row.getString("acc_id");
            System.out.println(acc_id + " : " + kw);
        }
    }
}
