package com.qa.ims.controller;

import java.util.List;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

    public static final Logger LOGGER = LogManager.getLogger();

    private ItemDAO itemDAO;
    private Utils utils;

    public ItemController(ItemDAO itemDAO, Utils utils) {
        super();
        this.itemDAO = itemDAO;
        this.utils = utils;
    }

    /**
     * Reads all items to the logger
     */
    @Override
    public List<Item> readAll() {
        List<Item> items = itemDAO.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }

    /**
     * Creates an item by taking in user input
     */
    @Override
    public Item create() {
        LOGGER.info("Please enter item name: ");
        String itemName = utils.getString();
        LOGGER.info("Please enter item value: ");
        Double itemValue = utils.getDouble();
        // enter address once everything is working
        Item item = itemDAO.create(new Item(itemName, itemValue));
        LOGGER.info("Item created.");
        return item;
    }

    /**
     * Updates an existing item by taking in user input
     */
    @Override
    public Item update() {
        LOGGER.info("Please enter the ID of the item you'd like to update: ");
        Long id = utils.getLong();

        if (!checkID(id)) return null;

        LOGGER.info("Please enter an item name: ");
        String name = utils.getString();
        LOGGER.info("Please enter the value of the item: ");
        Double value = utils.getDouble();
        Item item = itemDAO.update(new Item(id, name, value));
        LOGGER.info("Item successfully updated.");
        return item;
    }

    /**
     * Deletes an existing item using the id of the item
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the ID of the item you would like to delete: ");
        Long id = utils.getLong();

        if (!checkID(id)) return 0;

        LOGGER.info("Item successfully deleted.");
        return itemDAO.delete(id);
    }

    private boolean checkID(Long id) {
        List<Item> items = itemDAO.readAll();
        int count = 0;
        for (Item item : items) {
            if (item.getID() == id) count++;
        }
        if (count == 0) {
            LOGGER.info("Item does not exist");
            return false;
        } else return true;
    }
}

