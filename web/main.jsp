<%-- 
    Document   : main
    Created on : Mar 12, 2019, 10:45:42 PM
    Author     : nguye
--%>

<%@page import="DAO.PlantDAO"%>
<%@page import="model.Harvest"%>
<%@page import="support.load.StringCode"%>
<%@page import="model.Farm"%>
<%@page import="java.util.Base64"%>
<%@page import="java.io.ObjectOutputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Plant"%>
<%@page import="model.Player"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
        <script src="../jss/script.js"></script>
        <script>
            function doPlant() {
//                var item = document.getElementsByName('plantName');
//                var selectedItem;
//                for (var i = 0; i < item.length; i++) {
//                    if (item[i].type == 'radio' && item[i].checked == true) {
//                        selectedItem.value = item[i].value;
//                    }
//                }
//                var plantName = document.getElementById("plantNameForPlant");
//                plantName.value = selectedItem.value;
                var form = document.getElementById("plantForm");
                form.submit();
            }
        </script>
    </head>
    <%  Player player = (Player) request.getAttribute("player");
        Farm farm = (Farm) request.getAttribute("farm");
        ArrayList<Harvest> harvest = (ArrayList) request.getAttribute("harvest");

        String playerString = StringCode.encode(player);
        String farmString = StringCode.encode(farm);
        String harvestString = StringCode.encode(harvest);
    %>

    <body>
        <div id="mainDiv">
            <table id="mainTable">
                <tr>
                    <td id="col1">
                        <div id="playerInfor">
                            <span style="font-size: 25px"><%=player.getUsername()%></span>
                            <span style="font-size: 25px">Gold: <%=player.getGold()%></span>
                            <br/>
                            <br/>
                            <form action="login" method="get">
                                <input class="buttonstyle" type="submit" value="Log out" name="submit" />
                            </form>
                        </div>
                    </td>
                    <td id="col2">
                        <img src="img/happyfarm.png" style="height: 70px;text-align: center" />
                        <form action="harvest" method="get" style="margin-top:20px" >
                            <input type="hidden" name="player" value="<%=playerString%>" />
                            <input type="hidden" name="farm"  value="<%=farmString%>" />
                            <input type="hidden" name="harvest" value="<%=harvestString%>" />
                            <input type="submit" value="Harvest" name="submit">
                        </form>
                        <table id="farmland">
                            <%int cnt = 0; %>
                            <% for (int i = 1; i <= 4; i++) {%>
                            <tr>
                                <%for (int j = 1; j <= 8; j++) {%>
                                <td id="cell">

                                    <%Farm.Position p = farm.getPosElement(i, j);  %>
                                    <% if (p != null) { %>

                                    <%String url = "img/plant@" + p.getPlant().getId() + ".png";%>
                                    <div class="tooltip"><img src="<%=url%>" style="width: 100%; height: 100%"/>
                                        <span class="tooltiptext">
                                            <%=p.getPlant().getPlantName()%><br/>
                                            Planted from: <%=p.getDateStarted()%><br/>
                                            Season left: <%=p.getSeasonLeft()%> <br/>
                                        </span>
                                    </div>
                                    <%}%>

                                </td>
                                <%}%>
                            </tr>
                            <%}%>
                        </table>
                        `<div id="searchDiv">
                            <table>
                                <tr>
                                    <td>
                                        <form action="search" method="get">
                                            <input type="text" name="plantName" />
                                            <input type="hidden" name="player" value="<%=playerString%>">
                                            <input type="hidden" name="farm"  value="<%=farmString%>" />
                                            <input type="hidden" name="harvest" value="<%=harvestString%>" />
                                            <input type="submit" name="submit" value="Search">
                                            <%if (request.getAttribute("invalid") != null) {%>
                                            <%=request.getAttribute("invalid")%>
                                            <%}%>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <%ArrayList<Plant> plants = (ArrayList) request.getAttribute("plants"); %>
                            <form id="plantForm" action="plant" method="get">
                                <%if (plants != null) {%>
                                <table>

                                    <tr>
                                        <%for (Plant plant : plants) {%>
                                        <td>

                                            <%String url = "img/plant@" + plant.getId() + ".png";%>
                                            <img src="<%=url%>" style="width: 100px; height: 100px"/>
                                            <br/>
                                            <input type="radio" name="plantName" value="<%=plant.getPlantName()%>" />
                                            <%=plant.getPlantName()%><br/>
                                            Price: <%=plant.getPlantPrice()%><br/>
                                            Profit: <%=plant.getFruitPrice()%><br/>
                                            Season: <%=plant.getSeason()%><br/>
                                            <%=plant.getTime()%> days to harvest<br/>
                                        </td>
                                        <%}%>
                                    </tr>
                                </table>
                                <input type="hidden" name="player" value="<%=playerString%>">
                                <input type="hidden" name="farm"  value="<%=farmString%>" />
                                <input type="hidden" name="harvest" value="<%=harvestString%>" />
                                x:<input type="text" name="x" style="width: 50px"/>
                                y:<input type="text" name="y" style="width: 50px"/>
                                <button onclick="doPlant()">Plant</button>
                                <%}%>
                            </form>
                        </div>
                    </td>
                    <td id="col3">
                        <div id="cart">
                            <div id="harvest">
                                <b>Cart:</b><br/>

                                <%if (harvest != null) {%>
                                <table>
                                    <tr>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                    </tr>
                                    <%for (Harvest h : harvest) {%>
                                    <tr>
                                        <td><%=new PlantDAO().get(h.getPlantID()).getPlantName()%></td>
                                        <td><%=h.getQuantity()%></td>
                                        <td><%=new PlantDAO().get(h.getPlantID()).getFruitPrice()%></td>
                                    </tr>
                                    <%}%>
                                </table>
                                <form action="refresh" method="get">
                                    <input type="hidden" name="player" value="<%=playerString%>">
                                    <input type="hidden" name="farm"  value="<%=farmString%>" />
                                    <input type="hidden" name="harvest" value="<%=harvestString%>" />
                                    <input type="submit" name="submit" value="Refresh" />
                                </form>
                                <br/>
                                <form action="sell" method="get">
                                    <input type="hidden" name="player" value="<%=playerString%>">
                                    <input type="hidden" name="farm"  value="<%=farmString%>" />
                                    <input type="hidden" name="harvest" value="<%=harvestString%>" />
                                    <input type="submit" name="submit" value="Sell all" />
                                </form>
                                <%}%>
                            </div>

                        </div>

                    </td>
                </tr>
            </table>
        </div>
    </body>

</html>
