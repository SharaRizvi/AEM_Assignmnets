package com.ttn.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeaderComponent extends WCMUsePojo {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private ResourceResolver resourceResolver;
    private List<HeaderBean> submenuItems = new ArrayList<>();

    @Override
    public void activate() throws Exception {
        Node currentNode = getResource().adaptTo(Node.class);
        NodeIterator ni =  currentNode.getNodes();
        while (ni.hasNext()) {
            Node child = ni.nextNode();
            NodeIterator ni2 =  child.getNodes();
            setMultiFieldItems(ni2);
        }
    }

    private List<HeaderBean> setMultiFieldItems(NodeIterator ni2) {

        try{
            String title;
            String pageLink;
            while (ni2.hasNext()) {
                HeaderBean menuItem = new HeaderBean();
                Node grandChild = (Node)ni2.nextNode();
                title= grandChild.getProperty("title").getString();
                pageLink = grandChild.getProperty("pageLink").getString();
                log.info("*** PATH is "+pageLink);
                resourceResolver = getResourceResolver();
                Resource resource=resourceResolver.getResource(pageLink);
                List<ListPageDetail> dataFromModelList=new ArrayList<>();
                if(resource!=null){
                    try {
                        Page parentPage=resource.adaptTo(Page.class);
                        if(parentPage!=null){
                            Iterator<Page> listChildPages=parentPage.listChildren();
                            while(listChildPages.hasNext()){
                                Page childPage=listChildPages.next();
                                ListPageDetail detail=new ListPageDetail();
                                detail.setTitle(childPage.getTitle());
                                detail.setUrl(childPage.getPath());
                                dataFromModelList.add(detail);
                            }
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                menuItem.setTitle(title);
                menuItem.setPageLink(pageLink);
                menuItem.setDataFromModelList(dataFromModelList);
                submenuItems.add(menuItem);
            }
        }

        catch(Exception e){
        }
        return submenuItems;
    }

    public List<HeaderBean> getMultiFieldItems() {
        return submenuItems;
    }
}