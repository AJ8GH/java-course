package com.aj8gh.jfxchallenge.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactDaoTest {

    @Test
    public void testCreate() {
        Contact contact = new Contact();
        ContactDao.instance().add(contact);
        assertTrue(ContactDao.instance().getContacts().contains(contact));
    }
}
