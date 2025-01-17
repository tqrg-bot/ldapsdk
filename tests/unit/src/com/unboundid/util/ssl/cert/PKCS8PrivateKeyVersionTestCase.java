/*
 * Copyright 2017-2018 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2017-2018 Ping Identity Corporation
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
package com.unboundid.util.ssl.cert;



import org.testng.annotations.Test;

import com.unboundid.ldap.sdk.LDAPSDKTestCase;



/**
 * This class provides test coverage for the PKCS8PrivateKeyVersion class.
 */
public class PKCS8PrivateKeyVersionTestCase
       extends LDAPSDKTestCase
{
  /**
   * Performs a number of tests for the defined set of values.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testValues()
         throws Exception
  {
    for (final PKCS8PrivateKeyVersion v : PKCS8PrivateKeyVersion.values())
    {
      assertNotNull(v);

      assertNotNull(v.getIntValue());

      assertNotNull(v.getName());

      assertNotNull(PKCS8PrivateKeyVersion.valueOf(v.getIntValue()));
      assertEquals(PKCS8PrivateKeyVersion.valueOf(v.getIntValue()), v);

      assertNotNull(PKCS8PrivateKeyVersion.valueOf(v.name()));
      assertEquals(PKCS8PrivateKeyVersion.valueOf(v.name()), v);
    }
  }



  /**
   * Tests the behavior when attempting to use a nonexistent value.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testNonexistentValue()
         throws Exception
  {
    assertNull(PKCS8PrivateKeyVersion.valueOf(1234));

    try
    {
      PKCS8PrivateKeyVersion.valueOf("nonexistent");
      fail("Expected an exception from valueOf with a nonexistent name");
    }
    catch (final IllegalArgumentException iae)
    {
      // This was expected.
    }
  }
}
