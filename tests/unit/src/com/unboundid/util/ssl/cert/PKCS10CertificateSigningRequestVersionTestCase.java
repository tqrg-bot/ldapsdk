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
 * This class provides test coverage for the
 * PKCS10CertificateSigningRequestVersion class.
 */
public class PKCS10CertificateSigningRequestVersionTestCase
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
    for (final PKCS10CertificateSigningRequestVersion v :
         PKCS10CertificateSigningRequestVersion.values())
    {
      assertNotNull(v);

      assertNotNull(v.getIntValue());

      assertNotNull(v.getName());

      assertNotNull(PKCS10CertificateSigningRequestVersion.valueOf(
           v.getIntValue()));
      assertEquals(PKCS10CertificateSigningRequestVersion.valueOf(
           v.getIntValue()), v);

      assertNotNull(PKCS10CertificateSigningRequestVersion.valueOf(v.name()));
      assertEquals(PKCS10CertificateSigningRequestVersion.valueOf(v.name()), v);
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
    assertNull(PKCS10CertificateSigningRequestVersion.valueOf(1234));

    try
    {
      PKCS10CertificateSigningRequestVersion.valueOf("nonexistent");
      fail("Expected an exception from valueOf with a nonexistent name");
    }
    catch (final IllegalArgumentException iae)
    {
      // This was expected.
    }
  }
}
