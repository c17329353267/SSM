package com.aynu.documentmanage.dao;

import com.aynu.entity.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DocumentDao {
    List<Document> selectAllDocuments(Map map);

    int selectDocumentCounts(Document document);

    int uploadDocument(Document document);

    int deleteDocumentsByIds(@Param("IDS") Integer[] ids);

    List<String> selectDocumentsByIds(@Param("listIds") List<Integer> listIds);

    Document selectDocumentsById(Integer id);

    int updateDocument(Document document);


    //int selectDocumentCount(@Param("title") String title);
}
