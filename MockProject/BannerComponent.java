package com.ttn.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import java.util.ArrayList;
import java.util.List;

public class BannerComponent extends WCMUsePojo {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    private List<BannerBean> submenuItems = new ArrayList<>();

    @Override
    public void activate() throws Exception {

        Node currentNode = getResource().adaptTo(Node.class);

        NodeIterator ni =  currentNode.getNodes() ;

        while (ni.hasNext()) {

            Node child = ni.nextNode();

            NodeIterator ni2 =  child.getNodes() ;
            setMultiFieldItems(ni2);
        }
    }

    private List<BannerBean> setMultiFieldItems(NodeIterator ni2) {

        try{

            String heading;
            String subHeading;
            String ctaLink;
            String imgLink;

            //THIS WILL READ THE VALUE OF THE CORAL3 Multifield and set them in a collection
            while (ni2.hasNext()) {

                BannerBean menuItem = new BannerBean();


                Node grandChild = (Node)ni2.nextNode();

                log.info("*** GRAND CHILD NODE PATH IS "+grandChild.getPath());

                heading = grandChild.getProperty("heading").getString();
                subHeading = grandChild.getProperty("subHeading").getString();
                ctaLink = grandChild.getProperty("ctaLink").getString();
                imgLink = grandChild.getProperty("imgLink").getString();
                log.info("*** PATH is "+imgLink);

                menuItem.setHeading(heading);
                menuItem.setSubHeading(subHeading);
                menuItem.setCtaLink(ctaLink);
                menuItem.setImgLink(imgLink);
                submenuItems.add(menuItem);
            }
        }

        catch(Exception e){
            log.error("Exception while Multifield data {}", e.getMessage(), e);
        }
        return submenuItems;
    }

    public List<BannerBean> getMultiFieldItems() {
        return submenuItems;
    }
}
