/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
package com.floreantpos.model.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.floreantpos.Messages;
import com.floreantpos.PosException;
import com.floreantpos.model.MenuGroup;
import com.floreantpos.model.MenuItem;
import com.floreantpos.model.MenuItemModifierGroup;
import com.floreantpos.model.Terminal;

public class MenuItemDAO extends BaseMenuItemDAO {

	/**
	 * Default constructor. Can be used in place of getInstance()
	 */
	public MenuItemDAO() {
	}

	public MenuItem initialize(MenuItem menuItem) {
		if (menuItem.getId() == null)
			return menuItem;

		Session session = null;

		try {
			session = createNewSession();
			menuItem = (MenuItem) session.merge(menuItem);

			Hibernate.initialize(menuItem.getMenuItemModiferGroups());

			List<MenuItemModifierGroup> menuItemModiferGroups = menuItem.getMenuItemModiferGroups();
			if (menuItemModiferGroups != null) {
				for (MenuItemModifierGroup menuItemModifierGroup : menuItemModiferGroups) {
					Hibernate.initialize(menuItemModifierGroup.getModifierGroup().getModifiers());
				}
			}

			Hibernate.initialize(menuItem.getShifts());

			return menuItem;
		}
		finally {
			closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	public List<MenuItem> findByParent(Terminal terminal, MenuGroup group, boolean includeInvisibleItems) throws PosException {
		Session session = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.add(Restrictions.eq(MenuItem.PROP_PARENT, group));
			//
			criteria.addOrder(Order.asc(MenuItem.PROP_SORT_ORDER));

			if (!includeInvisibleItems) {
				criteria.add(Restrictions.eq(MenuItem.PROP_VISIBLE, Boolean.TRUE));
			}

			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PosException(Messages.getString("MenuItemDAO.0")); //$NON-NLS-1$
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<MenuItemModifierGroup> findModifierGroups(MenuItem item) throws PosException {
		Session session = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.add(Restrictions.eq(MenuItem.PROP_ID, item.getId()));
			MenuItem newItem = (MenuItem) criteria.uniqueResult();
			Hibernate.initialize(newItem.getMenuItemModiferGroups());

			return newItem.getMenuItemModiferGroups();
		} catch (Exception e) {
			throw new PosException(Messages.getString("MenuItemDAO.1")); //$NON-NLS-1$
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<MenuItem> getSimilar(String itemName, MenuGroup menuGroup) {
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSession();
			criteria = session.createCriteria(MenuItem.class);

			if (menuGroup != null ) {
				criteria.add(Restrictions.eq(MenuItem.PROP_PARENT, menuGroup));
			}
			
			if (StringUtils.isNotEmpty(itemName)) {
				criteria.add(Restrictions.ilike(MenuItem.PROP_NAME, itemName.trim(), MatchMode.ANYWHERE));
			}

			return criteria.list();

		} catch (Exception e) {
		}

		return criteria.list();

	}

}