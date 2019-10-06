package com.aynu.documentmanage.service;

import com.aynu.entity.Document;
import com.aynu.pages.PageModel;

import java.util.List;

public interface DocumentService {
    int findAllDocumentCounts(Document document);

    List<Document> findAllDocuments(Document document, PageModel pageModel);

    int addDocument(Document document);

    int removeDocumentsByIds(Integer[] ids);


    List<String> findDocumentsByIds(Integer[] ids);

    Document findDocumentsById(Integer id);

    int modifyDocument(Document document);
}
