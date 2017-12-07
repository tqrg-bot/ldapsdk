/*
 * Copyright 2017 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2017 Ping Identity Corporation
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



import com.unboundid.util.NotMutable;
import com.unboundid.util.OID;
import com.unboundid.util.ThreadSafety;
import com.unboundid.util.ThreadSafetyLevel;

import static com.unboundid.util.ssl.cert.CertMessages.*;



/**
 * This class provides an implementation of the issuer alternative name X.509
 * certificate extension as described in
 * <A HREF="https://www.ietf.org/rfc/rfc5280.txt">RFC 5280</A> section 4.2.1.7.
 * It can provide additional information about the issuer for a certificate, but
 * this information is generally not used in the course of validating a
 * certification path.
 * <BR><BR>
 * The OID for this extension is 2.5.29.18.  See the
 * {@link GeneralAlternativeNameExtension} class for implementation details and
 * the value encoding.
 */
@NotMutable()
@ThreadSafety(level=ThreadSafetyLevel.COMPLETELY_THREADSAFE)
public final class IssuerAlternativeNameExtension
       extends GeneralAlternativeNameExtension
{
  /**
   * The OID (2.5.29.18) for issuer alternative name extensions.
   */
  public static final OID ISSUER_ALTERNATIVE_NAME_OID = new OID("2.5.29.18");



  /**
   * The serial version UID for this serializable class.
   */
  private static final long serialVersionUID = -1448132657790331913L;



  /**
   * Creates a new issuer alternative name extension with the provided
   * information.
   *
   * @param  isCritical    Indicates whether this extension should be considered
   *                       critical.
   * @param  generalNames  The set of names to include in this extension.  This
   *                       must not be {@code null}.
   *
   * @throws  CertException  If a problem occurs while trying to encode the
   *                         value.
   */
  IssuerAlternativeNameExtension(final boolean isCritical,
                                 final GeneralNames generalNames)
       throws CertException
  {
    super(ISSUER_ALTERNATIVE_NAME_OID, isCritical, generalNames);
  }



  /**
   * Creates a new issuer alternative name extension from the provided generic
   * extension.
   *
   * @param  extension  The extension to decode as a issuer alternative name
   *                    extension.
   *
   * @throws  CertException  If the provided extension cannot be decoded as a
   *                         issuer alternative name extension.
   */
  IssuerAlternativeNameExtension(final X509CertificateExtension extension)
       throws CertException
  {
    super(extension);
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public String getExtensionName()
  {
    return INFO_ISSUER_ALT_NAME_EXTENSION_NAME.get();
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public void toString(final StringBuilder buffer)
  {
    toString("IssuerAlternativeNameExtension", buffer);
  }
}