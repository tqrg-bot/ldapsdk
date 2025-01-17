/*
 * Copyright 2014-2018 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2014-2018 Ping Identity Corporation
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
package com.unboundid.ldap.sdk.unboundidds.controls;



import org.testng.annotations.Test;

import com.unboundid.ldap.sdk.LDAPSDKTestCase;



/**
 * This class provides a set of test cases for the transaction settings commit
 * durability enum.
 */
public final class TransactionSettingsCommitDurabilityTestCase
       extends LDAPSDKTestCase
{
  /**
   * Provides test coverage for the various enum methods.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testEnumValues()
         throws Exception
  {
    for (final TransactionSettingsCommitDurability v :
         TransactionSettingsCommitDurability.values())
    {
      assertNotNull(v.name());
      assertEquals(TransactionSettingsCommitDurability.valueOf(v.name()), v);
      assertEquals(TransactionSettingsCommitDurability.valueOf(v.intValue()),
           v);
      assertNotNull(v.toString());
    }

    assertNull(TransactionSettingsCommitDurability.valueOf(12345));

    try
    {
      TransactionSettingsCommitDurability.valueOf("undefined");
      fail("Expected an exception from valueOf with an undefined string");
    }
    catch (final IllegalArgumentException e)
    {
      // This was expected.
    }
  }
}
