/*
 * Copyright 2010-2018 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2010-2018 Ping Identity Corporation
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPLv2 only)
 * or the terms of the GNU Lesser General Public License (LGPLv2.1 only)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */
package com.unboundid.ldap.sdk.controls;



import org.testng.annotations.Test;

import com.unboundid.ldap.sdk.LDAPSDKTestCase;



/**
 * This class provides a set of test cases for the
 * {@code ContentSyncInfoType} class.
 */
public final class ContentSyncInfoTypeTestCase
       extends LDAPSDKTestCase
{
  /**
   * Provides test coverage for the NEW_COOKIE type.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testNewCookie()
         throws Exception
  {
    final ContentSyncInfoType t = ContentSyncInfoType.NEW_COOKIE;

    assertNotNull(t.name());
    assertNotNull(t.toString());

    assertEquals(t.getType(), (byte) 0x80);

    assertNotNull(ContentSyncInfoType.valueOf(t.getType()));
    assertEquals(ContentSyncInfoType.valueOf(t.getType()), t);

    assertNotNull(ContentSyncInfoType.valueOf(t.name()));
    assertEquals(ContentSyncInfoType.valueOf(t.name()), t);
  }



  /**
   * Provides test coverage for the REFRESH_DELETE type.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testRefreshDelete()
         throws Exception
  {
    final ContentSyncInfoType t = ContentSyncInfoType.REFRESH_DELETE;

    assertNotNull(t.name());
    assertNotNull(t.toString());

    assertEquals(t.getType(), (byte) 0xA1);

    assertNotNull(ContentSyncInfoType.valueOf(t.getType()));
    assertEquals(ContentSyncInfoType.valueOf(t.getType()), t);

    assertNotNull(ContentSyncInfoType.valueOf(t.name()));
    assertEquals(ContentSyncInfoType.valueOf(t.name()), t);
  }



  /**
   * Provides test coverage for the REFRESH_PRESENT type.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testRefreshPresent()
         throws Exception
  {
    final ContentSyncInfoType t = ContentSyncInfoType.REFRESH_PRESENT;

    assertNotNull(t.name());
    assertNotNull(t.toString());

    assertEquals(t.getType(), (byte) 0xA2);

    assertNotNull(ContentSyncInfoType.valueOf(t.getType()));
    assertEquals(ContentSyncInfoType.valueOf(t.getType()), t);

    assertNotNull(ContentSyncInfoType.valueOf(t.name()));
    assertEquals(ContentSyncInfoType.valueOf(t.name()), t);
  }



  /**
   * Provides test coverage for the SYNC_ID_SET type.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testSyncIDSet()
         throws Exception
  {
    final ContentSyncInfoType t = ContentSyncInfoType.SYNC_ID_SET;

    assertNotNull(t.name());
    assertNotNull(t.toString());

    assertEquals(t.getType(), (byte) 0xA3);

    assertNotNull(ContentSyncInfoType.valueOf(t.getType()));
    assertEquals(ContentSyncInfoType.valueOf(t.getType()), t);

    assertNotNull(ContentSyncInfoType.valueOf(t.name()));
    assertEquals(ContentSyncInfoType.valueOf(t.name()), t);
  }



  /**
   * Provides general test coverage for the enum.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testGeneral()
         throws Exception
  {
    for (final ContentSyncInfoType t : ContentSyncInfoType.values())
    {
      assertNotNull(t);

      assertNotNull(ContentSyncInfoType.valueOf(t.getType()));
      assertEquals(ContentSyncInfoType.valueOf(t.getType()), t);

      assertNotNull(t.name());
      assertNotNull(ContentSyncInfoType.valueOf(t.name()));
      assertEquals(ContentSyncInfoType.valueOf(t.name()), t);
    }

    try
    {
      ContentSyncInfoType.valueOf("invalid");
      fail("Expected an exception for an invalid valueOf string");
    }
    catch (final Exception e)
    {
      // This was expected.
    }

    assertNull(ContentSyncInfoType.valueOf((byte) 0x00));
  }
}
