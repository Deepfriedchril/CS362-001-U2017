/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package finalprojectB;

import junit.framework.TestCase;




/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTestgood() throws Throwable {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   // Expected to be valid
       assertTrue(urlVal.isValid("http://www.amazon.ru/"));
       assertTrue(urlVal.isValid("http://www.google.com"));
       assertTrue(urlVal.isValid("http://www.gmail.com"));
       assertTrue(urlVal.isValid("https://www.tutorialspoint.com/junit/junit_test_framework.htm"));
       assertTrue(urlVal.isValid("http://oregonstate.edu/"));
       assertTrue(urlVal.isValid("http://www.oregon.gov/odot/dmv/Pages/index.aspx"));
       assertTrue(urlVal.isValid("https://www.fightforthefuture.org/"));
       assertTrue(urlVal.isValid("http://192.168.0.1:25565"));
       assertTrue(urlVal.isValid("ftp://192.0.0.1"));

       //urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_LOCAL_URLS);
       assertTrue(urlVal.isValid("h3t://testtest.com"));
       assertTrue(urlVal.isValid("file:///C:/folder/testing/"));
    }
    
    public void testManualTestExpectedbad() throws Throwable {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        // Expected to be bad
        assertFalse(urlVal.isValid("http://wwwcom"));
        assertFalse(urlVal.isValid("htp://badaddress.gov"));
        assertFalse(urlVal.isValid("http//badaddress.org"));
        assertFalse(urlVal.isValid("http:/misingaslash.com"));
        assertFalse(urlVal.isValid("//noscheme.com"));
        assertFalse(urlVal.isValid(""));
        assertFalse(urlVal.isValid("http://somethin.g"));
    }
   
        String schemeGood[] = {
                                "http://",
                                "file:///",
                                "https://",
                                "ftp://",
                                "h3t://",
                                ""
                            };

        String schemeBad[] = {
                                "htp://",
                                "file://",
                                "http:/",
                                "http//",
                                "://",
                            };

        String authorityGood[] = {
                                    "www.google.com",
                                    "192.168.1.1",
                                    "255.255.255.255",
                                    "test.com",
                                    "test.gov",
                                    "test.org",
                                    "somewhere.ru",
                                    "german.de",
                                    "taco.mx",
                                    "fish.se"
                                };

        String authorityBad[] = {
                                    "1.2.3.4.5",
                                    "test",
                                    "test.ing",
                                    ""
                                };
        String portsGood[] = {
                                "",
                                ":25565",
                                ":8080",
                                "",
                            };

        String portsBad[] = {
                                "1a2b3",
                                "-1000",
                                "123456789"
                            };

        String pathsGood[] = {
                                "/users/",
                                "/testing",
                                "/tests",
                                ""
                            };

        String pathsBad[] = {
                                "//",
                                "/../",
                                "/../tests",
                                "test/",
                                "//testing/"
                            };

        String quaryGood[] = {
                                "?action=view",
                                "?action=edit&mode=up",
                                ""
                            };

        String quaryBad[] = {
                                "?iamafakequary",
                                "?",
                                "??",
                                "???"
                            };
   
   public void testIsValid() {
        UrlValidator curUrl = new UrlValidator(null, null, 0);  
	    String tempString;
        int fail = 0;
        int partition = 0;
        int loop1, loop2, loop3;

        for(int i = 0; i < 5; i++) {
		    switch (i){
                case 0: //Scheme is changed only
                    System.out.println("\nTesting BAD Scheme");
                    for (partition = 0; partition < schemeBad.length-1; partition++){
                        for (loop1 = 0; loop1 < authorityGood.length-1; loop1++){
                            for (loop2 = 0; loop2 < portsGood.length-1; loop2++){
                                for (loop3 = 0; loop3 < quaryGood.length-1; loop3++){
                                    tempString = (
                                        schemeBad[partition] +
                                        authorityGood[loop1] +
                                        portsGood[loop2] +
                                        //pathsGood[loop3]
                                        quaryGood[loop3]
                                        );
                                    try{
                                        assertFalse(curUrl.isValid(tempString));
                                    } catch (AssertionError e) {
                                        fail++;
                                        System.out.println("Should be FALSE: " + tempString);
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 1: // authority is changed
                    System.out.println("\nTesting BAD authority");
                    for (partition = 0; partition < schemeGood.length-1; partition++){
                        for (loop1 = 0; loop1 < authorityBad.length-1; loop1++){
                            for (loop2 = 0; loop2 < portsGood.length-1; loop2++){
                                for (loop3 = 0; loop3 < quaryGood.length-1; loop3++){
                                    tempString = (
                                        schemeGood[partition] +
                                        authorityBad[loop1] +
                                        portsGood[loop2] +
                                        quaryGood[loop3]
                                        //pathsGood[loop3]
                                        );
                                    try{
                                        assertFalse(curUrl.isValid(tempString));
                                    } catch (AssertionError e) {
                                        fail++;
                                        System.out.println("Should be FALSE: " + tempString);
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 2: // ports is changed
                    System.out.println("\nTesting BAD ports");
                    for (partition = 0; partition < schemeGood.length-1; partition++){
                        for (loop1 = 0; loop1 < authorityGood.length-1; loop1++){
                            for (loop2 = 0; loop2 < portsBad.length-1; loop2++){
                                for (loop3 = 0; loop3 < quaryGood.length-1; loop3++){
                                    tempString = (
                                        schemeGood[partition] +
                                        authorityGood[loop1] +
                                        portsBad[loop2] +
                                        quaryGood[loop3]
                                        //pathsGood[loop3]
                                        );
                                    try{
                                        assertFalse(curUrl.isValid(tempString));
                                    } catch (AssertionError e) {
                                        fail++;
                                        System.out.println("Should be FALSE: " + tempString);
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 3: // bad quary
                    System.out.println("\nTesting with bad quary");
                    for (partition = 0; partition < schemeGood.length-1; partition++){
                        for (loop1 = 0; loop1 < authorityGood.length-1; loop1++){
                            for (loop2 = 0; loop2 < portsGood.length-1; loop2++){
                                for (loop3 = 0; loop3 < quaryBad.length-1; loop3++){
                                    tempString = (
                                        schemeGood[partition] +
                                        authorityGood[loop1] +
                                        portsGood[loop2] +
                                        quaryBad[loop3]
                                        //pathsGood[loop3]
                                        );
                                    try{
                                        assertFalse(curUrl.isValid(tempString));
                                    } catch (AssertionError e) {
                                        fail++;
                                        System.out.println("Should be FALSE: " + tempString);
                                    }
                                }
                            }
                        }
                    }
                    break;


                case 4: // All Good
                    System.out.println("\nTesting known Good Combos");
                    for (partition = 0; partition < schemeGood.length-1; partition++){
                        for (loop1 = 0; loop1 < authorityGood.length-1; loop1++){
                            for (loop2 = 0; loop2 < portsGood.length-1; loop2++){
                                for (loop3 = 0; loop3 < quaryGood.length-1; loop3++){
                                    tempString = (
                                        schemeGood[partition] +
                                        authorityGood[loop1] +
                                        portsGood[loop2] +
                                        quaryGood[loop3]
                                        //pathsGood[loop3]
                                        );
                                    try{
                                        assertTrue(curUrl.isValid(tempString));
                                    } catch (AssertionError e) {
                                        fail++;
                                        System.out.println("Should be TRUE: " + tempString);
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
	    }
        if (fail > 0) fail();
   }
///////////////////////
}
