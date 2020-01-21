#import "RNConfig.h"

@implementation RNConfig

RCT_EXPORT_MODULE();

- (NSDictionary *)constantsToExport
{
  return [[NSBundle mainBundle] infoDictionary];
}

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
