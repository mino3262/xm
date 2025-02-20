package com.app.xm.common;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import com.app.xm.entity.Influxdb;
import com.app.xm.entity.InfluxdbResult;

public class CustomInfluxdb {

    public InfluxDB getInfluxDB(Influxdb influxdb) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://"+influxdb.getInfluxdbUrl(), influxdb.getInfluxdbUsername(),
                influxdb.getInfluxdbPassword());
        influxDB.setDatabase(influxdb.getInfluxdbDatabase());
        return influxDB;
    }


    //一次查询
    public List<QueryResult.Result> queryData(Influxdb influxdb) {
        InfluxDB influxDB = getInfluxDB(influxdb);
        Query query = new Query(influxdb.getDataFetchQuery());
        QueryResult queryResult = influxDB.query(query);
        influxDB.close();
        return queryResult.getResults();
    }


    //包装返回
    public List<InfluxdbResult> queryListData(Influxdb influxdb) {
        List<InfluxdbResult> influxdbResults = new ArrayList<>();
        InfluxDB influxDB = getInfluxDB(influxdb);
        Query query = new Query(influxdb.getDataFetchQuery());
        QueryResult queryResult = influxDB.query(query);
        List<QueryResult.Result> results = queryResult.getResults();
        results.forEach(result -> {
         List<QueryResult.Series> seriesList = result.getSeries();
         seriesList.forEach(series -> {
             List<String> columns = series.getColumns();
             List<List<Object>> values = series.getValues();
             for(int i = 0; i < values.size(); i++) {
                 InfluxdbResult influxdbResult = new InfluxdbResult();
                 influxdbResult.setFields(new HashMap<>());
                 for(int j = 0; j < columns.size(); j++) {
                     String column = columns.get(j);
                     Object value = values.get(i).get(j);
                     if("time".equals(column)) {
                         influxdbResult.setTime(Timestamp.from(ZonedDateTime.parse(String.valueOf(value)).toInstant()).getTime());
                     }else {
                         influxdbResult.getFields().put(column, value);
                     }
                 }
                 influxdbResults.add(influxdbResult);
             }
         });
        });
        influxDB.close();
        return influxdbResults;
    }
}
