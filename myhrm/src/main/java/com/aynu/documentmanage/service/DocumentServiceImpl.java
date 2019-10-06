package com.aynu.documentmanage.service;

import com.aynu.documentmanage.dao.DocumentDao;
import com.aynu.entity.Document;
import com.aynu.pages.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;
    @Override
    public int findAllDocumentCounts(Document document) {
        return documentDao.selectDocumentCounts(document);
    }

    @Override
    public List<Document> findAllDocuments(Document document, PageModel pageModel) {
        Map map = new HashMap();
        map.put("document",document);
        map.put("pageModel",pageModel);
        return documentDao.selectAllDocuments(map);
    }

    @Override
    public int addDocument(Document document) {
        return documentDao.uploadDocument(document);
    }

    @Override
    public int removeDocumentsByIds(Integer[] ids) {
        return documentDao.deleteDocumentsByIds(ids);
    }

    @Override
    public List<String> findDocumentsByIds(Integer[] ids) {
        List<Integer> listIds= new ArrayList<>();
        for (Integer id:ids
             ) {
            listIds.add(id);
        }
        return documentDao.selectDocumentsByIds(listIds);
    }

    @Override
    public Document findDocumentsById(Integer id) {
        return documentDao.selectDocumentsById(id);
    }

    @Override
    public int modifyDocument(Document document) {
        return documentDao.updateDocument(document);
    }


}
