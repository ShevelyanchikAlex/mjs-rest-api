package com.epam.esm.hateoas.processor;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.hateoas.model.GiftCertificateModel;
import com.epam.esm.hateoas.model.TagModel;
import com.epam.esm.repository.filter.condition.GiftCertificateFilterCondition;
import com.epam.esm.service.pagination.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateModelProcessor implements RepresentationModelProcessor<TagModel> {
    @Override
    public TagModel process(TagModel model) {
        return model;
    }

    public CollectionModel<GiftCertificateModel> process(Page<GiftCertificateDto> page, int size, CollectionModel<GiftCertificateModel> collectionModel) {
        int nextPage = page.getNextPageIndex();
        int previousPage = page.getPreviousPageIndex();
        int lastPage = page.getTotalPages();
        Link previousPageLink = linkTo(findAllMethod(previousPage, size))
                .withRel("prev")
                .expand();
        Link nextPageLink = linkTo(findAllMethod(nextPage, size))
                .withRel("next")
                .expand();
        Link firstPageLink = linkTo(findAllMethod(Page.FIRST_PAGE, size))
                .withRel("first")
                .expand();
        Link lastPageLink = linkTo(findAllMethod(lastPage, size))
                .withRel("last")
                .expand();
        return collectionModel.add(previousPageLink, nextPageLink, firstPageLink, lastPageLink);
    }

    private CollectionModel<GiftCertificateModel> findAllMethod(int pageIndex, int size) {
        return methodOn(GiftCertificateController.class).findAll(pageIndex, size);
    }

    public CollectionModel<GiftCertificateModel> process(Page<GiftCertificateDto> page, int size,
                                                         GiftCertificateFilterCondition giftCertificateFilterCondition,
                                                         CollectionModel<GiftCertificateModel> collectionModel) {
        int nextPage = page.getNextPageIndex();
        int previousPage = page.getPreviousPageIndex();
        int lastPage = page.getTotalPages();
        Link previousPageLink = linkTo(findWithFilterMethod(previousPage, size, giftCertificateFilterCondition))
                .withRel("prev")
                .expand();
        Link nextPageLink = linkTo(findWithFilterMethod(nextPage, size, giftCertificateFilterCondition))
                .withRel("next")
                .expand();
        Link firstPageLink = linkTo(findWithFilterMethod(Page.FIRST_PAGE, size, giftCertificateFilterCondition))
                .withRel("first")
                .expand();
        Link lastPageLink = linkTo(findWithFilterMethod(lastPage, size, giftCertificateFilterCondition))
                .withRel("last")
                .expand();
        return collectionModel.add(previousPageLink, nextPageLink, firstPageLink, lastPageLink);
    }

    private CollectionModel<GiftCertificateModel> findWithFilterMethod(int pageIndex, int size, GiftCertificateFilterCondition giftCertificateFilterCondition) {
        return methodOn(GiftCertificateController.class).findWithFilter(pageIndex, size, giftCertificateFilterCondition);
    }
}