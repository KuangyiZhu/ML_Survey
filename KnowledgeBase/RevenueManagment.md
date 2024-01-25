## Revenue Management
Revenue management is the application of disciplined analytics that predict consumer behaviour at the micro-market 
level and optimize product availability and price to maximize revenue growth. The primary aim of revenue management 
is selling the right product to the right customer at the right time for the right price and with the right pack. 
The essence of this discipline is in understanding customers' perception of product value and accurately aligning 
product prices, placement and availability with each customer segment.[1]

## Inventory Model
The mathematical approach is typically formulated as follows: a store has, at time {\displaystyle k} k, {\displaystyle x_{k}} x_{k} items in stock. It then orders (and receives) {\displaystyle u_{k}} u_{k} items, and sells {\displaystyle w_{k}} w_k items, where {\displaystyle w} w follows a given probability distribution. Thus:

{\displaystyle x_{k+1}=x_{k}+u_{k}-w_{k}} {\displaystyle x_{k+1}=x_{k}+u_{k}-w_{k}}
{\displaystyle u_{k}\geq 0} {\displaystyle u_{k}\geq 0}
Whether {\displaystyle x_{k}} x_{k} is allowed to go negative, corresponding to back-ordered items, will depend on the specific situation; if allowed there will usually be a penalty for back orders. The store has costs that are related to the number of items in store and the number of items ordered:

{\displaystyle c_{k}=c(x_{k},u_{k})} {\displaystyle c_{k}=c(x_{k},u_{k})}. Often this will be in additive form: {\displaystyle c_{k}=p(x_{k})+h(u_{k})} {\displaystyle c_{k}=p(x_{k})+h(u_{k})}
The store wants to select {\displaystyle u_{k}} u_{k} in an optimal way, i.e. to minimize

{\displaystyle \sum _{k=0}^{T}c_{k}} {\displaystyle \sum _{k=0}^{T}c_{k}}.
