/*
 * Copyright (c) 2017-2018 
 *
 * by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com) & DAW students
 * 
 * GESANE: Free Open Source Health Management System
 *
 * Sources at:
 *                            https://github.com/rafaelaznar/gesane-server
 *                            https://github.com/rafaelaznar/gesane-client
 *                            https://github.com/rafaelaznar/gesane-database
 *
 * GESANE is distributed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package factory;

import connection.specificimplementation.C3POConnection;
import connection.publicinterface.ConnectionInterface;
import connection.specificimplementation.BoneCPConnection;
import connection.specificimplementation.DBCPConnection;
import connection.specificimplementation.DriverManagerConnection;
import connection.specificimplementation.HikariConnection;
import connection.specificimplementation.ViburConnection;

public class ConnectionFactory {

    public static ConnectionInterface getSourceConnection(String strConnection) throws Exception {
        ConnectionInterface oDataConnectionSource = null;
        switch (strConnection) {
            case "hikari":
                oDataConnectionSource = new HikariConnection();
                break;
            case "vibur":
                oDataConnectionSource = new ViburConnection();
                break;
            case "c3po":
                oDataConnectionSource = new C3POConnection();
                break;
            case "dbcp":
                oDataConnectionSource = new DBCPConnection();
                break;
            case "driver":
                oDataConnectionSource = new DriverManagerConnection();
                break;
            case "bone":
                oDataConnectionSource = new BoneCPConnection();
                break;
            default:
                break;
        }

        return oDataConnectionSource;
    }

}
