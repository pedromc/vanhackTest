package com.faire.vanhackTest;

import com.faire.vanhackTest.faireAPI.FaireAPIStub;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @org.junit.jupiter.api.Test
    void invetoryTest() {

        Inventory inv = Inventory.getInstance(FaireAPIStub.BRAND_ODD, new FaireAPIStub());
        inv.refreshInventory();
        assertTrue(inv.checkItemInvetory("po_1-p_1"));
        assertEquals(10, inv.getProductOptionAvailableQuantity("po_1-p_1"));
        assertTrue(inv.consumeProductOption("po_1-p_1", 4));
        assertEquals(6, inv.getProductOptionAvailableQuantity("po_1-p_1"));
        assertTrue(inv.consumeProductOption("po_1-p_1", 6));
        assertEquals(0, inv.getProductOptionAvailableQuantity("po_1-p_1"));
        assertFalse(inv.consumeProductOption("po_1-p_1", 6));

    }
}