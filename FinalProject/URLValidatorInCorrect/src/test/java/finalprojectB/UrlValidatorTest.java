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
       assertTrue(urlVal.isValid("http://www.amazon.com/"));
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
   
   
    public void testYourFirstPartition() {
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
                                    "test.org"
                                };

        String authorityBad[] = {
                                    "1.2.3.4.5",
                                    "test",
                                    "test.ing",
                                    ""
                                };
        String portsGood[] = {
                                ":80",
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
        

    }   
   
   public void testYourSecondPartition(){
	   
   }
   
   
   public void testIsValid() {
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
                                    "test.org"
                                };

        String authorityBad[] = {
                                    "1.2.3.4.5",
                                    "test",
                                    "test.ing",
                                    ""
                                };
        String portsGood[] = {
                                ":80",
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

        UrlValidator curUrl = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);  
	    
        for(int i = 0; i < scheme.length; i++) {
		    for (int j = 0; j < authorityGood.lenght; j++){
                try {
                    assertTrue(curUrl.isValid(schemeGood[ThreadLocalRrandom.nextInt()]))
                }
            }
	    }


   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
