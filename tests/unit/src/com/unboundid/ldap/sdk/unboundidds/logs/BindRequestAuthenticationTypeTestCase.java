/*
 * Copyright 2009-2018 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2009-2018 Ping Identity Corporation
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
package com.unboundid.ldap.sdk.unboundidds.logs;



import org.testng.annotations.Test;

import com.unboundid.ldap.sdk.LDAPSDKTestCase;



/**
 * This class provides a set of test cases for the
 * {@code BindRequestAuthenticationType} class.
 */
public class BindRequestAuthenticationTypeTestCase
     extends LDAPSDKTestCase
{
  /**
   * Provides test coverage for the {@code values} and {@code valueOf} methods
   * to ensure that all values can be retrieved using {@code valueOf}.
   *
   * @throws  Exception  If an unexpected value occurs.
   */
  @Test()
  public void testValuesAndValueOf()
         throws Exception
  {
    for (BindRequestAuthenticationType t :
         BindRequestAuthenticationType.values())
    {
      assertNotNull(BindRequestAuthenticationType.valueOf(t.name()));
      assertEquals(BindRequestAuthenticationType.valueOf(t.name()), t);
    }
  }



  /**
   * Tests the {@code valueOf} method with an invalid value.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test(expectedExceptions = { IllegalArgumentException.class })
  public void testValueOfInvalid()
         throws Exception
  {
    BindRequestAuthenticationType.valueOf("invalid");
  }
}
