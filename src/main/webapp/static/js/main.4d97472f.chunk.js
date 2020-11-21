(this["webpackJsonpcollege-marketplace"]=this["webpackJsonpcollege-marketplace"]||[]).push([[0],{203:function(e,t,a){e.exports=a(357)},211:function(e,t){},213:function(e,t){},245:function(e,t){},246:function(e,t){},316:function(e,t,a){},357:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),l=a(20),c=a.n(l),s=a(18),o=a(369),i=a(194),u=a(11),m=a(46),p=a(55),d=a(92),E=a(366),h=a(367),f=a(361),b=a(76),g=(window.location.host,function(e){var t=e.show,a=e.handleClose,l=e.updateUser,c=Object(n.useState)(!0),o=Object(s.a)(c,2),i=o[0],u=o[1],m=Object(n.useState)({}),g=Object(s.a)(m,2),y=g[0],O=g[1],v=Object(n.useState)(!1),S=Object(s.a)(v,2),j=S[0],w=S[1];return r.a.createElement(E.a,{show:t,onHide:a},r.a.createElement(E.a.Header,null,i?"Log in":"Sign up"),r.a.createElement(E.a.Body,null,r.a.createElement(h.a,{onChange:function(e){O(Object(d.a)({},y,Object(p.a)({},e.target.name,e.target.value)))},onSubmit:function(e){if(e.preventDefault(),e.stopPropagation(),!0===e.currentTarget.checkValidity()){w(!0);var t={username:y.username},n=Object(b.SHA256)(y.username+":"+y.password).toString();t.hash=n,console.info("POST",i?"http://127.0.0.1:8080/api/login":"http://127.0.0.1:8080/api/signup",t),fetch(i?"http://127.0.0.1:8080/api/login":"http://127.0.0.1:8080/api/signup",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)}).then((function(e){200===e.status?(l(y.username),sessionStorage.setItem("username",y.username),w(!1),a()):(alert("Invalid email/password!"),w(!1))}))}}},r.a.createElement(h.a.Group,{controlId:"email"},r.a.createElement(h.a.Label,null,"Username"),r.a.createElement(h.a.Control,{required:!0,name:"username",type:"text",placeholder:"Username"})),r.a.createElement(h.a.Group,{controlId:"password"},r.a.createElement(h.a.Label,null,"Password"),r.a.createElement(h.a.Control,{required:!0,name:"password",type:"password",placeholder:"Password"})),r.a.createElement(h.a.Row,{style:{alignItems:"center"}},r.a.createElement(f.a,{className:"mr-auto ml-1",variant:"primary",type:"submit",disabled:j},j?"Processing...":"Submit"),r.a.createElement("i",{className:"mr-1",style:{cursor:"pointer"},onClick:function(){return u(!i)}},r.a.createElement("u",null,i?"Don't have an account?":"Have an account?"))))))}),y=function(e){var t=e.username,a=e.updateUser,l=Object(n.useState)(!1),c=Object(s.a)(l,2),p=c[0],d=c[1],E=Object(u.e)();return r.a.createElement(r.a.Fragment,null,r.a.createElement(o.a,{bg:"primary",position:"static",variant:"dark",expand:!0},r.a.createElement(i.a,{md:3},r.a.createElement(o.a.Brand,{as:m.b,to:"/",className:"mr-auto"},"USC Marketplace")),r.a.createElement(i.a,{md:6}),r.a.createElement(i.a,{md:3,style:{textAlign:"right"}},r.a.createElement(o.a.Text,{onClick:function(){t?E.push("/user"):d(!0)},style:{cursor:"pointer"}},t||"Log in"))),r.a.createElement(g,{show:p,handleClose:function(){return d(!1)},updateUser:a}))},O=(a(316),a(80)),v=a(370),S=a(371),j=a(362),w=a(363),C=a(364),P=function(e){var t=e.username,a=e.searchitems,l=Object(n.useState)(a||[]),c=Object(s.a)(l,2),o=c[0],i=c[1],u=Object(n.useState)(1),p=Object(s.a)(u,2),d=p[0],E=p[1];Object(n.useEffect)((function(){a||(console.info("GET http://127.0.0.1:8080/api/items"),fetch("http://127.0.0.1:8080/api/items").then((function(e){return e.json()})).then((function(e){return i(e)})))}),[]);var h=function(e){E(e),window.scrollTo(0,0)},b=o&&o.slice(20*(d-1),20*d).map((function(e){return r.a.createElement("tr",{key:e.itemid},r.a.createElement(v.a,{trigger:"hover",placement:"auto-start",overlay:r.a.createElement(S.a,null,r.a.createElement(S.a.Content,null,e.description))},r.a.createElement("td",{style:{width:"50%"}},e.name)),r.a.createElement("td",{style:{width:"20%"}},"$",e.price),r.a.createElement("td",{style:{width:"20%"}},e.seller),r.a.createElement("td",{style:{width:"10%"}},r.a.createElement(f.a,{onClick:function(){return a=e.itemid,console.info("POST http://127.0.0.1:8080/api/buy",{itemid:a,username:t}),void fetch("http://127.0.0.1:8080/api/buy",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify({itemid:a,username:t})}).then((function(e){200===e.status?alert("Successful. Waiting for seller approval now."):alert("Item is not available.")}));var a},disabled:!t},"Buy now")))}));return r.a.createElement(r.a.Fragment,null,r.a.createElement(j.a,{fluid:"lg",className:"my-5"},t?r.a.createElement(f.a,{as:m.b,to:"/create",className:"mb-3"},"Create new listing"):"",0!==o.length?r.a.createElement(r.a.Fragment,null,r.a.createElement(w.a,{hover:!0},r.a.createElement("thead",null,r.a.createElement("th",null,"Name"),r.a.createElement("th",null,"Price"),r.a.createElement("th",null,"Seller"),r.a.createElement("th",null)),r.a.createElement("tbody",null,b)),1!==d?r.a.createElement(f.a,{onClick:function(){return h(d-1)},className:"mr-3"},"Previous"):"",d!==Math.ceil(o.length/20)?r.a.createElement(f.a,{onClick:function(){return h(d+1)}},"Next"):""):r.a.createElement(C.a,{animation:"border",variant:"primary",className:"loading-spinner"})))},T=a(133),N=a(365),k=a(368),I=function(e){var t=e.username,a=Object(n.useState)("orders"),l=Object(s.a)(a,2),c=l[0],o=l[1],u=Object(n.useState)([]),m=Object(s.a)(u,2),p=m[0],d=m[1];Object(n.useEffect)((function(){console.info("GET","http://127.0.0.1:8080/api/user/"+t),fetch("http://127.0.0.1:8080/api/user/"+t).then((function(e){return e.json()})).then((function(e){d(e)}))}),[t]);var E;return r.a.createElement(r.a.Fragment,null,r.a.createElement(N.a,{className:"mt-5"},r.a.createElement(i.a,{md:{span:2,offset:1}},r.a.createElement(k.a,{variant:"pills",defaultActiveKey:"orders",className:"flex-column",onSelect:function(e){return o(e)}},r.a.createElement(k.a.Item,null,r.a.createElement(k.a.Link,{eventKey:"orders"},"Requests")),r.a.createElement(k.a.Item,null,r.a.createElement(k.a.Link,{eventKey:"password"},"Change password")))),r.a.createElement(i.a,{md:{span:6},className:"ml-5"},"password"===c?r.a.createElement(r.a.Fragment,null,r.a.createElement("h3",null,"Change password"),r.a.createElement(h.a,{onSubmit:function(e){e.preventDefault(),e.stopPropagation();var a=new FormData(e.target);""!==a.get("password").trim()?(console.info("POST","http://127.0.0.1:8080/api/user/password",{username:t,hash:Object(b.SHA256)(t+":"+a.get("password")).toString()}),fetch("http://127.0.0.1:8080/api/user/password",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify({username:t,hash:Object(b.SHA256)(t+":"+a.get("password")).toString()})}).then((function(e){200===e.status?alert("Password changed successfully!"):alert("An error has occured! Please try again.")}))):alert("Please enter a valid password")}},r.a.createElement(h.a.Group,{controlId:"name"},r.a.createElement(h.a.Label,null,"Username"),r.a.createElement(h.a.Control,{type:"text",placeholder:"Enter your name",defaultValue:t,disabled:!0})),r.a.createElement(h.a.Group,{controlId:"password"},r.a.createElement(h.a.Label,null,"Password"),r.a.createElement(h.a.Control,{type:"password",placeholder:"Password",name:"password"})),r.a.createElement(f.a,{variant:"primary",type:"submit"},"Save changes"))):r.a.createElement(r.a.Fragment,null,r.a.createElement("h3",null,"Incoming Requests"),r.a.createElement(w.a,null,r.a.createElement("thead",null,r.a.createElement("th",null,"Name"),r.a.createElement("th",null,"Price"),r.a.createElement("th",null,"Buyer"),r.a.createElement("th",null,"Status")),r.a.createElement("tbody",null,(E=p)&&E.map((function(e){return r.a.createElement("tr",{key:e.itemid},r.a.createElement("td",null,e.name),r.a.createElement("td",null,"$",e.price),r.a.createElement("td",null,e.buyer),r.a.createElement("td",null,r.a.createElement(f.a,{onClick:function(){return function(e){console.info("POST","http://127.0.0.1:8080/api/user/approve",{username:t,itemid:e.itemid}),fetch("http://127.0.0.1:8080/api/user/approve",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify({seller:t,buyer:e.buyer,itemid:e.itemid})}).then((function(t){if(200===t.status){alert("Successful");var a=p.findIndex((function(t){return t.itemid===e.itemid}));d([].concat(Object(T.a)(p.slice(0,a)),Object(T.a)(p.slice(a+1))))}else alert("An error has occured.")}))}(e)}},"Approve")))}))))))))},x=function(e){var t=e.username,a=Object(n.useState)({}),l=Object(s.a)(a,2),c=l[0],o=l[1],i=Object(n.useState)(!1),m=Object(s.a)(i,2),E=m[0],b=m[1],g=Object(u.e)();return r.a.createElement(j.a,{fluid:"md",className:"mt-4"},r.a.createElement("h2",null,"Create new listing"),r.a.createElement(h.a,{onChange:function(e){o(Object(d.a)({},c,Object(p.a)({},e.target.name,e.target.value)))},onSubmit:function(e){e.preventDefault(),e.stopPropagation(),e.currentTarget.checkValidity()&&(b(!0),console.info("POST","http://127.0.0.1:8080/api/sell",c),fetch("http://127.0.0.1:8080/api/sell",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(c)}).then((function(e){200===e.status?(alert("Successful"),g.push("/")):alert("An error has occured."),b(!1)})))}},r.a.createElement(h.a.Group,{controlId:"title"},r.a.createElement(h.a.Label,null,"Title"),r.a.createElement(h.a.Control,{type:"text",name:"title",required:!0})),r.a.createElement(h.a.Group,{controlId:"price"},r.a.createElement(h.a.Label,null,"Price"),r.a.createElement(h.a.Control,{type:"number",name:"price",required:!0})),r.a.createElement(h.a.Group,{controlId:"description"},r.a.createElement(h.a.Label,null,"Description"),r.a.createElement(h.a.Control,{as:"textarea",rows:5,name:"description",required:!0})),r.a.createElement("input",{type:"hidden",name:"username",value:t}),r.a.createElement(f.a,{type:"submit",disabled:E},"Submit")))};new FormData;var L=function(e){var t=e.username,a=Object(u.f)();return r.a.createElement(O.a,{className:"p-0"},t&&r.a.createElement(O.a,null,r.a.createElement(u.a,{path:"/user"},r.a.createElement(I,{username:t})),r.a.createElement(u.a,{path:"/create"},r.a.createElement(x,{username:t}))),a.state?r.a.createElement(P,{username:t,searchitems:a.state}):r.a.createElement(u.a,{path:"/",exact:!0},r.a.createElement(P,{username:t})))},A=a(100),G=a(198),D=a.n(G),F=a(199),J=a.n(F);var U=function(){var e=Object(A.useToasts)().addToast,t=Object(n.useState)(null),a=Object(s.a)(t,2),l=a[0],c=a[1];return Object(n.useEffect)((function(){var e=sessionStorage.getItem("username");e!==l&&c(e)}),[]),Object(n.useEffect)((function(){var t=new D.a("/push_notif"),a=J.a.over(t);return a.debug=function(){},a.connect({},(function(t){a.subscribe("/topic/messages",(function(t){var a=JSON.parse(t.body);e(a.buyer+" just bought "+a.item+"!",{appearance:"info"})}))})),function(){a.disconnect()}}),[e]),r.a.createElement(m.a,{basename:""},r.a.createElement(y,{username:l,updateUser:function(e){return c(e)}}),r.a.createElement(L,{username:l}))};c.a.render(r.a.createElement(r.a.StrictMode,null,r.a.createElement(A.ToastProvider,{placement:"top-center",autoDismiss:!0},r.a.createElement(U,null))),document.getElementById("root"))}},[[203,1,2]]]);
//# sourceMappingURL=main.4d97472f.chunk.js.map