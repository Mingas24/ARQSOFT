// @ts-nocheck
import { GraphQLResolveInfo, SelectionSetNode, FieldNode } from 'graphql';
import { findAndParseConfig } from '@graphql-mesh/cli';
import { createMeshHTTPHandler, MeshHTTPHandler } from '@graphql-mesh/http';
import { getMesh, ExecuteMeshFn, SubscribeMeshFn, MeshContext as BaseMeshContext, MeshInstance } from '@graphql-mesh/runtime';
import { MeshStore, FsStoreStorageAdapter } from '@graphql-mesh/store';
import { path as pathModule } from '@graphql-mesh/cross-helpers';
import { ImportFn } from '@graphql-mesh/types';
import type { SandwichMTypes } from './sources/SandwichM/types';
import type { ShopTypes } from './sources/Shop/types';
import type { OrderManagementTypes } from './sources/OrderManagement/types';
import type { PromotionManagementTypes } from './sources/PromotionManagement/types';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type RequireFields<T, K extends keyof T> = Omit<T, K> & { [P in K]-?: NonNullable<T[P]> };



/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};

export type Query = {
  listPromotions?: Maybe<Array<Maybe<PromotionDTO>>>;
  getPromotionById?: Maybe<PromotionDTO>;
  getActiveLocalPromotionBySandwich?: Maybe<PromotionDTO>;
  getActiveGlobalPromotionBySandwich?: Maybe<PromotionDTO>;
  getOrderHistoryByCostumer?: Maybe<Array<Maybe<OrderDTO>>>;
  allSandwich: Array<Sandwiches>;
  sandwichById?: Maybe<Sandwiches>;
  getAllShops?: Maybe<Array<Maybe<ShopDTO>>>;
  getShopById?: Maybe<ShopDTO>;
};


export type QuerygetPromotionByIdArgs = {
  promotionId: Scalars['Int'];
};


export type QuerygetActiveLocalPromotionBySandwichArgs = {
  sandwichId: Scalars['Int'];
  shopId: Scalars['Int'];
};


export type QuerygetActiveGlobalPromotionBySandwichArgs = {
  sandwichId: Scalars['Int'];
};


export type QuerygetOrderHistoryByCostumerArgs = {
  costumerEmail?: InputMaybe<Scalars['String']>;
};


export type QuerysandwichByIdArgs = {
  sandwichId: Scalars['Int'];
};


export type QuerygetShopByIdArgs = {
  shopId: Scalars['Int'];
};

export type Mutation = {
  addPromotion?: Maybe<PromotionDTO>;
  deletePromotionById?: Maybe<Scalars['Int']>;
  editPromotion?: Maybe<Scalars['Int']>;
  addOrder?: Maybe<OrderDTO>;
  approveOrder?: Maybe<Scalars['Int']>;
  cancelOrder?: Maybe<Scalars['Int']>;
  saveSandwich: Sandwiches;
  updateSandwich: Sandwiches;
  deleteSandwich: Scalars['String'];
  /**     addShop(designation: String!, email: String!, address: String!, shopSchedule: [Map]!) : ShopDto! */
  addShop: ShopDTO;
  deleteShop?: Maybe<Scalars['Int']>;
  changePromotionApplication?: Maybe<Scalars['Int']>;
  editShop?: Maybe<Scalars['Int']>;
};


export type MutationaddPromotionArgs = {
  inputPromotionDTO: InputPromotionDTO;
};


export type MutationdeletePromotionByIdArgs = {
  promotionId: Scalars['Int'];
};


export type MutationeditPromotionArgs = {
  promotionId: Scalars['Int'];
  inputPromotionDTO: InputPromotionDTO;
};


export type MutationaddOrderArgs = {
  inputOrderDTO: InputOrderDTO;
};


export type MutationapproveOrderArgs = {
  orderId: Scalars['Float'];
};


export type MutationcancelOrderArgs = {
  orderId: Scalars['Float'];
};


export type MutationsaveSandwichArgs = {
  newSandwich: SandwichesInput;
};


export type MutationupdateSandwichArgs = {
  updateSandwich: SandwichesInput;
};


export type MutationdeleteSandwichArgs = {
  sandwichId: Scalars['Int'];
};


export type MutationaddShopArgs = {
  inputShopDTO?: InputMaybe<InputShopDTO>;
};


export type MutationdeleteShopArgs = {
  shopId: Scalars['Int'];
};


export type MutationchangePromotionApplicationArgs = {
  shopId: Scalars['Int'];
  applicationType: Scalars['String'];
};


export type MutationeditShopArgs = {
  shopId: Scalars['Int'];
  designation: Scalars['String'];
  email: Scalars['String'];
  address: Scalars['String'];
  shopSchedule: Array<InputMaybe<Map>>;
};

export type InputPromotionDTO = {
  sandwichId?: InputMaybe<Scalars['ID']>;
  type?: InputMaybe<Scalars['String']>;
  shopId?: InputMaybe<Scalars['ID']>;
  percentage?: InputMaybe<Scalars['Float']>;
  startDate?: InputMaybe<Scalars['String']>;
  endDate?: InputMaybe<Scalars['String']>;
};

export type PromotionDTO = {
  promotionId?: Maybe<Scalars['ID']>;
  type?: Maybe<Scalars['String']>;
  sandwichId?: Maybe<Scalars['ID']>;
  shopId?: Maybe<Scalars['ID']>;
  percentage?: Maybe<Scalars['Float']>;
  startDate?: Maybe<Scalars['String']>;
  endDate?: Maybe<Scalars['String']>;
};

export type StatusCode =
  | 'OK'
  | 'CREATED'
  | 'UPDATED'
  | 'DELETED'
  | 'BAD_REQUEST'
  | 'ACCESS_DENIED'
  | 'NOT_FOUND';

export type InputOrderDTO = {
  orderDate: Scalars['String'];
  shopId: Scalars['Int'];
  costumerEmail: Scalars['String'];
  sandwiches: Array<InputMaybe<Map>>;
};

export type Map = {
  first?: InputMaybe<Scalars['String']>;
  second?: InputMaybe<Scalars['String']>;
};

export type OrderDTO = {
  orderDate?: Maybe<Scalars['String']>;
  shopId?: Maybe<Scalars['Float']>;
  costumerEmail?: Maybe<Scalars['String']>;
  status?: Maybe<Scalars['String']>;
};

export type SandwichesInput = {
  sandwichId: Scalars['Int'];
  sandwichPrice?: InputMaybe<Scalars['String']>;
  sandwichDescription?: InputMaybe<Scalars['String']>;
  sandwichDesignation?: InputMaybe<Scalars['String']>;
};

export type Sandwiches = {
  sandwichId: Scalars['Int'];
  sandwichPrice?: Maybe<Scalars['String']>;
  sandwichDescription?: Maybe<Scalars['String']>;
  sandwichDesignation?: Maybe<Scalars['String']>;
};

export type InputShopDTO = {
  designation?: InputMaybe<Scalars['String']>;
  email?: InputMaybe<Scalars['String']>;
  address?: InputMaybe<Scalars['String']>;
  schedule?: InputMaybe<Array<InputMaybe<Map>>>;
  promotionApplication?: InputMaybe<Scalars['String']>;
};

export type PromotionApplication =
  | 'Cumulative'
  | 'MostFavourable';

export type Shop = {
  id?: Maybe<Scalars['ID']>;
  designation?: Maybe<Scalars['String']>;
  email?: Maybe<Scalars['String']>;
  address?: Maybe<Scalars['String']>;
  shopSchedule?: Maybe<Array<Maybe<ShopSchedule>>>;
  promotionApplication?: Maybe<PromotionApplication>;
};

export type ShopDTO = {
  shop_id?: Maybe<Scalars['ID']>;
  designation?: Maybe<Scalars['String']>;
  email?: Maybe<Scalars['String']>;
  address?: Maybe<Scalars['String']>;
  schedule?: Maybe<Array<Maybe<ShopScheduleDTO>>>;
  promotionApplication?: Maybe<PromotionApplication>;
};

export type ShopSchedule = {
  schedule_id?: Maybe<Scalars['ID']>;
  day?: Maybe<Scalars['String']>;
  date?: Maybe<Scalars['String']>;
};

export type ShopScheduleDTO = {
  schedule_id?: Maybe<Scalars['ID']>;
  day?: Maybe<Scalars['String']>;
  date?: Maybe<Scalars['String']>;
};

export type WithIndex<TObject> = TObject & Record<string, any>;
export type ResolversObject<TObject> = WithIndex<TObject>;

export type ResolverTypeWrapper<T> = Promise<T> | T;


export type ResolverWithResolve<TResult, TParent, TContext, TArgs> = {
  resolve: ResolverFn<TResult, TParent, TContext, TArgs>;
};

export type LegacyStitchingResolver<TResult, TParent, TContext, TArgs> = {
  fragment: string;
  resolve: ResolverFn<TResult, TParent, TContext, TArgs>;
};

export type NewStitchingResolver<TResult, TParent, TContext, TArgs> = {
  selectionSet: string | ((fieldNode: FieldNode) => SelectionSetNode);
  resolve: ResolverFn<TResult, TParent, TContext, TArgs>;
};
export type StitchingResolver<TResult, TParent, TContext, TArgs> = LegacyStitchingResolver<TResult, TParent, TContext, TArgs> | NewStitchingResolver<TResult, TParent, TContext, TArgs>;
export type Resolver<TResult, TParent = {}, TContext = {}, TArgs = {}> =
  | ResolverFn<TResult, TParent, TContext, TArgs>
  | ResolverWithResolve<TResult, TParent, TContext, TArgs>
  | StitchingResolver<TResult, TParent, TContext, TArgs>;

export type ResolverFn<TResult, TParent, TContext, TArgs> = (
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => Promise<TResult> | TResult;

export type SubscriptionSubscribeFn<TResult, TParent, TContext, TArgs> = (
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => AsyncIterable<TResult> | Promise<AsyncIterable<TResult>>;

export type SubscriptionResolveFn<TResult, TParent, TContext, TArgs> = (
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => TResult | Promise<TResult>;

export interface SubscriptionSubscriberObject<TResult, TKey extends string, TParent, TContext, TArgs> {
  subscribe: SubscriptionSubscribeFn<{ [key in TKey]: TResult }, TParent, TContext, TArgs>;
  resolve?: SubscriptionResolveFn<TResult, { [key in TKey]: TResult }, TContext, TArgs>;
}

export interface SubscriptionResolverObject<TResult, TParent, TContext, TArgs> {
  subscribe: SubscriptionSubscribeFn<any, TParent, TContext, TArgs>;
  resolve: SubscriptionResolveFn<TResult, any, TContext, TArgs>;
}

export type SubscriptionObject<TResult, TKey extends string, TParent, TContext, TArgs> =
  | SubscriptionSubscriberObject<TResult, TKey, TParent, TContext, TArgs>
  | SubscriptionResolverObject<TResult, TParent, TContext, TArgs>;

export type SubscriptionResolver<TResult, TKey extends string, TParent = {}, TContext = {}, TArgs = {}> =
  | ((...args: any[]) => SubscriptionObject<TResult, TKey, TParent, TContext, TArgs>)
  | SubscriptionObject<TResult, TKey, TParent, TContext, TArgs>;

export type TypeResolveFn<TTypes, TParent = {}, TContext = {}> = (
  parent: TParent,
  context: TContext,
  info: GraphQLResolveInfo
) => Maybe<TTypes> | Promise<Maybe<TTypes>>;

export type IsTypeOfResolverFn<T = {}, TContext = {}> = (obj: T, context: TContext, info: GraphQLResolveInfo) => boolean | Promise<boolean>;

export type NextResolverFn<T> = () => Promise<T>;

export type DirectiveResolverFn<TResult = {}, TParent = {}, TContext = {}, TArgs = {}> = (
  next: NextResolverFn<TResult>,
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => TResult | Promise<TResult>;

/** Mapping between all available schema types and the resolvers types */
export type ResolversTypes = ResolversObject<{
  Query: ResolverTypeWrapper<{}>;
  Mutation: ResolverTypeWrapper<{}>;
  Boolean: ResolverTypeWrapper<Scalars['Boolean']>;
  Float: ResolverTypeWrapper<Scalars['Float']>;
  ID: ResolverTypeWrapper<Scalars['ID']>;
  InputPromotionDTO: InputPromotionDTO;
  Int: ResolverTypeWrapper<Scalars['Int']>;
  PromotionDTO: ResolverTypeWrapper<PromotionDTO>;
  StatusCode: StatusCode;
  String: ResolverTypeWrapper<Scalars['String']>;
  InputOrderDTO: InputOrderDTO;
  Map: Map;
  OrderDTO: ResolverTypeWrapper<OrderDTO>;
  SandwichesInput: SandwichesInput;
  Sandwiches: ResolverTypeWrapper<Sandwiches>;
  InputShopDTO: InputShopDTO;
  PromotionApplication: PromotionApplication;
  Shop: ResolverTypeWrapper<Shop>;
  ShopDTO: ResolverTypeWrapper<ShopDTO>;
  ShopSchedule: ResolverTypeWrapper<ShopSchedule>;
  ShopScheduleDTO: ResolverTypeWrapper<ShopScheduleDTO>;
}>;

/** Mapping between all available schema types and the resolvers parents */
export type ResolversParentTypes = ResolversObject<{
  Query: {};
  Mutation: {};
  Boolean: Scalars['Boolean'];
  Float: Scalars['Float'];
  ID: Scalars['ID'];
  InputPromotionDTO: InputPromotionDTO;
  Int: Scalars['Int'];
  PromotionDTO: PromotionDTO;
  String: Scalars['String'];
  InputOrderDTO: InputOrderDTO;
  Map: Map;
  OrderDTO: OrderDTO;
  SandwichesInput: SandwichesInput;
  Sandwiches: Sandwiches;
  InputShopDTO: InputShopDTO;
  Shop: Shop;
  ShopDTO: ShopDTO;
  ShopSchedule: ShopSchedule;
  ShopScheduleDTO: ShopScheduleDTO;
}>;

export type deferDirectiveArgs = {
  label?: Maybe<Scalars['String']>;
  if?: Maybe<Scalars['Boolean']>;
};

export type deferDirectiveResolver<Result, Parent, ContextType = MeshContext, Args = deferDirectiveArgs> = DirectiveResolverFn<Result, Parent, ContextType, Args>;

export type streamDirectiveArgs = {
  label?: Maybe<Scalars['String']>;
  initialCount?: Scalars['Int'];
  if?: Maybe<Scalars['Boolean']>;
};

export type streamDirectiveResolver<Result, Parent, ContextType = MeshContext, Args = streamDirectiveArgs> = DirectiveResolverFn<Result, Parent, ContextType, Args>;

export type QueryResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['Query'] = ResolversParentTypes['Query']> = ResolversObject<{
  listPromotions?: Resolver<Maybe<Array<Maybe<ResolversTypes['PromotionDTO']>>>, ParentType, ContextType>;
  getPromotionById?: Resolver<Maybe<ResolversTypes['PromotionDTO']>, ParentType, ContextType, RequireFields<QuerygetPromotionByIdArgs, 'promotionId'>>;
  getActiveLocalPromotionBySandwich?: Resolver<Maybe<ResolversTypes['PromotionDTO']>, ParentType, ContextType, RequireFields<QuerygetActiveLocalPromotionBySandwichArgs, 'sandwichId' | 'shopId'>>;
  getActiveGlobalPromotionBySandwich?: Resolver<Maybe<ResolversTypes['PromotionDTO']>, ParentType, ContextType, RequireFields<QuerygetActiveGlobalPromotionBySandwichArgs, 'sandwichId'>>;
  getOrderHistoryByCostumer?: Resolver<Maybe<Array<Maybe<ResolversTypes['OrderDTO']>>>, ParentType, ContextType, Partial<QuerygetOrderHistoryByCostumerArgs>>;
  allSandwich?: Resolver<Array<ResolversTypes['Sandwiches']>, ParentType, ContextType>;
  sandwichById?: Resolver<Maybe<ResolversTypes['Sandwiches']>, ParentType, ContextType, RequireFields<QuerysandwichByIdArgs, 'sandwichId'>>;
  getAllShops?: Resolver<Maybe<Array<Maybe<ResolversTypes['ShopDTO']>>>, ParentType, ContextType>;
  getShopById?: Resolver<Maybe<ResolversTypes['ShopDTO']>, ParentType, ContextType, RequireFields<QuerygetShopByIdArgs, 'shopId'>>;
}>;

export type MutationResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['Mutation'] = ResolversParentTypes['Mutation']> = ResolversObject<{
  addPromotion?: Resolver<Maybe<ResolversTypes['PromotionDTO']>, ParentType, ContextType, RequireFields<MutationaddPromotionArgs, 'inputPromotionDTO'>>;
  deletePromotionById?: Resolver<Maybe<ResolversTypes['Int']>, ParentType, ContextType, RequireFields<MutationdeletePromotionByIdArgs, 'promotionId'>>;
  editPromotion?: Resolver<Maybe<ResolversTypes['Int']>, ParentType, ContextType, RequireFields<MutationeditPromotionArgs, 'promotionId' | 'inputPromotionDTO'>>;
  addOrder?: Resolver<Maybe<ResolversTypes['OrderDTO']>, ParentType, ContextType, RequireFields<MutationaddOrderArgs, 'inputOrderDTO'>>;
  approveOrder?: Resolver<Maybe<ResolversTypes['Int']>, ParentType, ContextType, RequireFields<MutationapproveOrderArgs, 'orderId'>>;
  cancelOrder?: Resolver<Maybe<ResolversTypes['Int']>, ParentType, ContextType, RequireFields<MutationcancelOrderArgs, 'orderId'>>;
  saveSandwich?: Resolver<ResolversTypes['Sandwiches'], ParentType, ContextType, RequireFields<MutationsaveSandwichArgs, 'newSandwich'>>;
  updateSandwich?: Resolver<ResolversTypes['Sandwiches'], ParentType, ContextType, RequireFields<MutationupdateSandwichArgs, 'updateSandwich'>>;
  deleteSandwich?: Resolver<ResolversTypes['String'], ParentType, ContextType, RequireFields<MutationdeleteSandwichArgs, 'sandwichId'>>;
  addShop?: Resolver<ResolversTypes['ShopDTO'], ParentType, ContextType, Partial<MutationaddShopArgs>>;
  deleteShop?: Resolver<Maybe<ResolversTypes['Int']>, ParentType, ContextType, RequireFields<MutationdeleteShopArgs, 'shopId'>>;
  changePromotionApplication?: Resolver<Maybe<ResolversTypes['Int']>, ParentType, ContextType, RequireFields<MutationchangePromotionApplicationArgs, 'shopId' | 'applicationType'>>;
  editShop?: Resolver<Maybe<ResolversTypes['Int']>, ParentType, ContextType, RequireFields<MutationeditShopArgs, 'shopId' | 'designation' | 'email' | 'address' | 'shopSchedule'>>;
}>;

export type PromotionDTOResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['PromotionDTO'] = ResolversParentTypes['PromotionDTO']> = ResolversObject<{
  promotionId?: Resolver<Maybe<ResolversTypes['ID']>, ParentType, ContextType>;
  type?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  sandwichId?: Resolver<Maybe<ResolversTypes['ID']>, ParentType, ContextType>;
  shopId?: Resolver<Maybe<ResolversTypes['ID']>, ParentType, ContextType>;
  percentage?: Resolver<Maybe<ResolversTypes['Float']>, ParentType, ContextType>;
  startDate?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  endDate?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
}>;

export type OrderDTOResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['OrderDTO'] = ResolversParentTypes['OrderDTO']> = ResolversObject<{
  orderDate?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  shopId?: Resolver<Maybe<ResolversTypes['Float']>, ParentType, ContextType>;
  costumerEmail?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  status?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
}>;

export type SandwichesResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['Sandwiches'] = ResolversParentTypes['Sandwiches']> = ResolversObject<{
  sandwichId?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  sandwichPrice?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  sandwichDescription?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  sandwichDesignation?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
}>;

export type ShopResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['Shop'] = ResolversParentTypes['Shop']> = ResolversObject<{
  id?: Resolver<Maybe<ResolversTypes['ID']>, ParentType, ContextType>;
  designation?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  email?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  address?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  shopSchedule?: Resolver<Maybe<Array<Maybe<ResolversTypes['ShopSchedule']>>>, ParentType, ContextType>;
  promotionApplication?: Resolver<Maybe<ResolversTypes['PromotionApplication']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
}>;

export type ShopDTOResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['ShopDTO'] = ResolversParentTypes['ShopDTO']> = ResolversObject<{
  shop_id?: Resolver<Maybe<ResolversTypes['ID']>, ParentType, ContextType>;
  designation?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  email?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  address?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  schedule?: Resolver<Maybe<Array<Maybe<ResolversTypes['ShopScheduleDTO']>>>, ParentType, ContextType>;
  promotionApplication?: Resolver<Maybe<ResolversTypes['PromotionApplication']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
}>;

export type ShopScheduleResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['ShopSchedule'] = ResolversParentTypes['ShopSchedule']> = ResolversObject<{
  schedule_id?: Resolver<Maybe<ResolversTypes['ID']>, ParentType, ContextType>;
  day?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  date?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
}>;

export type ShopScheduleDTOResolvers<ContextType = MeshContext, ParentType extends ResolversParentTypes['ShopScheduleDTO'] = ResolversParentTypes['ShopScheduleDTO']> = ResolversObject<{
  schedule_id?: Resolver<Maybe<ResolversTypes['ID']>, ParentType, ContextType>;
  day?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  date?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
}>;

export type Resolvers<ContextType = MeshContext> = ResolversObject<{
  Query?: QueryResolvers<ContextType>;
  Mutation?: MutationResolvers<ContextType>;
  PromotionDTO?: PromotionDTOResolvers<ContextType>;
  OrderDTO?: OrderDTOResolvers<ContextType>;
  Sandwiches?: SandwichesResolvers<ContextType>;
  Shop?: ShopResolvers<ContextType>;
  ShopDTO?: ShopDTOResolvers<ContextType>;
  ShopSchedule?: ShopScheduleResolvers<ContextType>;
  ShopScheduleDTO?: ShopScheduleDTOResolvers<ContextType>;
}>;

export type DirectiveResolvers<ContextType = MeshContext> = ResolversObject<{
  defer?: deferDirectiveResolver<any, any, ContextType>;
  stream?: streamDirectiveResolver<any, any, ContextType>;
}>;

export type MeshContext = PromotionManagementTypes.Context & OrderManagementTypes.Context & SandwichMTypes.Context & ShopTypes.Context & BaseMeshContext;


const baseDir = pathModule.join(typeof __dirname === 'string' ? __dirname : '/', '..');

const importFn: ImportFn = <T>(moduleId: string) => {
  const relativeModuleId = (pathModule.isAbsolute(moduleId) ? pathModule.relative(baseDir, moduleId) : moduleId).split('\\').join('/').replace(baseDir + '/', '');
  switch(relativeModuleId) {
    default:
      return Promise.reject(new Error(`Cannot find module '${relativeModuleId}'.`));
  }
};

const rootStore = new MeshStore('.mesh', new FsStoreStorageAdapter({
  cwd: baseDir,
  importFn,
  fileType: "ts",
}), {
  readonly: true,
  validate: false
});

export function getMeshOptions() {
  console.warn('WARNING: These artifacts are built for development mode. Please run "mesh build" to build production artifacts');
  return findAndParseConfig({
    dir: baseDir,
    artifactsDir: ".mesh",
    configName: "mesh",
    additionalPackagePrefixes: [],
    initialLoggerPrefix: "🕸️  Mesh",
  });
}

export function createBuiltMeshHTTPHandler(): MeshHTTPHandler<MeshContext> {
  return createMeshHTTPHandler<MeshContext>({
    baseDir,
    getBuiltMesh: getBuiltMesh,
    rawServeConfig: {"endpoint":"/graphql"},
  })
}

let meshInstance$: Promise<MeshInstance> | undefined;

export function getBuiltMesh(): Promise<MeshInstance> {
  if (meshInstance$ == null) {
    meshInstance$ = getMeshOptions().then(meshOptions => getMesh(meshOptions)).then(mesh => {
      const id = mesh.pubsub.subscribe('destroy', () => {
        meshInstance$ = undefined;
        mesh.pubsub.unsubscribe(id);
      });
      return mesh;
    });
  }
  return meshInstance$;
}

export const execute: ExecuteMeshFn = (...args) => getBuiltMesh().then(({ execute }) => execute(...args));

export const subscribe: SubscribeMeshFn = (...args) => getBuiltMesh().then(({ subscribe }) => subscribe(...args));