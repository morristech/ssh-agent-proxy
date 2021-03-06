/*-
 * -\-\-
 * ssh-agent-proxy
 * --
 * Copyright (C) 2016 Spotify AB
 * --
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -/-/-
 */

/**
 * Copyright (c) 2015 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spotify.sshagentproxy;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface AgentProxy extends Closeable {

  /**
   * Get a list of public keys from the ssh-agent.
   * @return A list of {@link Identity}
   */
  List<Identity> list() throws IOException;

  /**
   * Ask the ssh-agent to hash and sign some data in the form of an array of bytes.
   *
   * According to the ssh-agent specs:
   * "Upon receiving this request, the agent will look up the private key that
   * corresponds to the public key contained in key_blob. It will use this
   * private key to sign the "data" and produce a signature blob using the
   * key type-specific method described in RFC 4253 section 6.6 "Public Key
   * Algorithms".
   * @param identity    An array of bytes for data to be signed.
   * @param data        An array of bytes for data to be signed.
   * @return            An array of bytes of signed data.
   */
  byte[] sign(final Identity identity, final byte[] data) throws IOException;
}
