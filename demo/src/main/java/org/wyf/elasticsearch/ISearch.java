package org.wyf.elasticsearch;

import java.util.Map;

public interface ISearch {

    Map<String, Object> save(String index, String type, String id, Map<String, Object> data);

    int update(String index, String type, String id, Map<String, Object> data);

    int delete(String index, String type, String id);
 
    Map<String, Object> get(String index, String type, String id);
    
    long searchES(String index, String type);
}
