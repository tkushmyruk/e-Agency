<#import "parts/common.ftlh" as c/>
<@c.main>
  <h2 align="center">Your Account</h2>
  <h3>${user.getUsername()}</h3>
  <a href="/change-password">Change password</a>
  <a href="/creditCard"/${user.getUsername()}>Your Card</a>
  <a href="/bought-tours">BoughtTours</a>
</@c.main>