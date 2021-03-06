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
package service.genericimplementation;

import service.publicinterface.ViewServiceInterface;
import com.google.gson.Gson;
import bean.helper.ReplyBeanHelper;
import connection.publicinterface.ConnectionInterface;
import helper.constant.ConfigurationConstants;
import helper.EncodingHelper;
import bean.helper.FilterBeanHelper;
import bean.helper.MetaBeanHelper;
import helper.Log4jHelper;
import factory.DaoFactory;
import helper.ParameterHelper;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import dao.publicinterface.ViewDaoInterface;
import factory.ConnectionFactory;
import helper.constant.ConnectionConstants;
import helper.GsonHelper;

public abstract class ViewGenericServiceImplementation extends MetaGenericServiceImplementation implements ViewServiceInterface {

    public ViewGenericServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ReplyBeanHelper getPage() throws Exception {
        if (this.checkPermission("getpage")) {
            int np = Integer.parseInt(oRequest.getParameter("np"));
            int rpp = Integer.parseInt(oRequest.getParameter("rpp"));
            String strOrder = oRequest.getParameter("order");
            String strFilter = oRequest.getParameter("filter");
            LinkedHashMap<String, String> hmOrder = ParameterHelper.getOrderParams(strOrder);
            ArrayList<FilterBeanHelper> alFilter = ParameterHelper.getFilterParams(strFilter);
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBeanHelper oReplyBean = null;
            MetaBeanHelper oMetaBeanHelper = null;
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                ViewDaoInterface oDao = (ViewDaoInterface) DaoFactory.getDao(ob, oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                oMetaBeanHelper = (MetaBeanHelper) oDao.getPage(rpp, np, hmOrder, alFilter, ConfigurationConstants.jsonMsgDepth);
                String strJson = GsonHelper.getGson().toJson(oMetaBeanHelper);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

    @Override
    public ReplyBeanHelper getCount() throws Exception {
        if (this.checkPermission("getcount")) {
            Long lResult;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBeanHelper oReplyBean = null;
            String strFilter = oRequest.getParameter("filter");
            ArrayList<FilterBeanHelper> alFilter = ParameterHelper.getFilterParams(strFilter);
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                ViewDaoInterface oDao = (ViewDaoInterface) DaoFactory.getDao(ob, oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                lResult = oDao.getCount(alFilter);
                String strJson = GsonHelper.getGson().toJson(lResult);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

    public ReplyBeanHelper getPageX() throws Exception {
        if (this.checkPermission("getpagex")) {
            int np = Integer.parseInt(oRequest.getParameter("np"));
            int rpp = Integer.parseInt(oRequest.getParameter("rpp"));
            int id_foreign = Integer.parseInt(oRequest.getParameter("id_foreign"));
            String ob_foreign = oRequest.getParameter("ob_foreign");
            String strOrder = oRequest.getParameter("order");
            String strFilter = oRequest.getParameter("filter");
            LinkedHashMap<String, String> hmOrder = ParameterHelper.getOrderParams(strOrder);
            ArrayList<FilterBeanHelper> alFilter = ParameterHelper.getFilterParams(strFilter);
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBeanHelper oReplyBean = null;
            MetaBeanHelper oMetaBeanHelper = null;
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                ViewDaoInterface oDao = (ViewDaoInterface) DaoFactory.getDao(ob, oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                oMetaBeanHelper = (MetaBeanHelper) oDao.getPageX(id_foreign, ob_foreign, rpp, np, hmOrder, alFilter, ConfigurationConstants.jsonMsgDepth);
                String strJson = GsonHelper.getGson().toJson(oMetaBeanHelper);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

    @Override
    public ReplyBeanHelper getCountX() throws Exception {
        if (this.checkPermission("getcountx")) {
            Long lResult;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBeanHelper oReplyBean = null;
            int id_foreign = Integer.parseInt(oRequest.getParameter("id_foreign"));
            String ob_foreign = oRequest.getParameter("ob_foreign");
            String strFilter = oRequest.getParameter("filter");
            ArrayList<FilterBeanHelper> alFilter = ParameterHelper.getFilterParams(strFilter);
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                ViewDaoInterface oDao = (ViewDaoInterface) DaoFactory.getDao(ob, oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                lResult = oDao.getCountX(id_foreign, ob_foreign, alFilter);
                Gson oGson = GsonHelper.getGson();
                String strJson = oGson.toJson(lResult);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

}

