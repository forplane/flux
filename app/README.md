```
subscriber就是订阅者，拿到class，然后用subscriberMethodFinder拿到subscriber中注册的方法
Class<?> subscriberClass = subscriber.getClass();
List<SubscriberMethod> subscriberMethods = subscriberMethodFinder.findSubscriberMethods(subscriberClass);


List<SubscriberMethod> findSubscriberMethods(Class<?> subscriberClass) {
        //METHOD_CACHE
        List<SubscriberMethod> subscriberMethods = METHOD_CACHE.get(subscriberClass);
        if (subscriberMethods != null) {
            return subscriberMethods;
        }

        if (ignoreGeneratedIndex) {
            subscriberMethods = findUsingReflection(subscriberClass);
        } else {
            subscriberMethods = findUsingInfo(subscriberClass);
        }
        if (subscriberMethods.isEmpty()) {
            throw new EventBusException("Subscriber " + subscriberClass
                    + " and its super classes have no public methods with the @Subscribe annotation");
        } else {
            METHOD_CACHE.put(subscriberClass, subscriberMethods);
            return subscriberMethods;
        }
}

```