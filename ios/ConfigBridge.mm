#import "ConfigBridge.h"

@implementation ConfigBridge
RCT_EXPORT_MODULE()

- (NSDictionary *)constantsToExport {
    return [[NSBundle mainBundle] infoDictionary];
}

+ (BOOL)requiresMainQueueSetup {
    return NO;
}


@end
