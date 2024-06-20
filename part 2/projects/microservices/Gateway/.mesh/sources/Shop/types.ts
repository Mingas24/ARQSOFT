// @ts-nocheck

import { InContextSdkMethod } from '@graphql-mesh/types';
import { MeshContext } from '@graphql-mesh/runtime';

export namespace ShopTypes {
  export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};

export type InputShopDTO = {
  designation?: InputMaybe<Scalars['String']>;
  email?: InputMaybe<Scalars['String']>;
  address?: InputMaybe<Scalars['String']>;
  schedule?: InputMaybe<Array<InputMaybe<Map>>>;
  promotionApplication?: InputMaybe<Scalars['String']>;
};

export type Map = {
  first?: InputMaybe<Scalars['String']>;
  second?: InputMaybe<Scalars['String']>;
};

export type Mutation = {
  /**     addShop(designation: String!, email: String!, address: String!, shopSchedule: [Map]!) : ShopDto! */
  addShop: ShopDTO;
  deleteShop?: Maybe<Scalars['Int']>;
  changePromotionApplication?: Maybe<Scalars['Int']>;
  editShop?: Maybe<Scalars['Int']>;
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

export type PromotionApplication =
  | 'Cumulative'
  | 'MostFavourable';

export type Query = {
  getAllShops?: Maybe<Array<Maybe<ShopDTO>>>;
  getShopById?: Maybe<ShopDTO>;
};


export type QuerygetShopByIdArgs = {
  shopId: Scalars['Int'];
};

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

export type StatusCode =
  | 'OK'
  | 'CREATED'
  | 'UPDATED'
  | 'DELETED'
  | 'BAD_REQUEST'
  | 'ACCESS_DENIED'
  | 'NOT_FOUND';

  export type QuerySdk = {
      /** null **/
  getAllShops: InContextSdkMethod<Query['getAllShops'], {}, MeshContext>,
  /** null **/
  getShopById: InContextSdkMethod<Query['getShopById'], QuerygetShopByIdArgs, MeshContext>
  };

  export type MutationSdk = {
      /**     addShop(designation: String!, email: String!, address: String!, shopSchedule: [Map]!) : ShopDto! **/
  addShop: InContextSdkMethod<Mutation['addShop'], MutationaddShopArgs, MeshContext>,
  /** null **/
  deleteShop: InContextSdkMethod<Mutation['deleteShop'], MutationdeleteShopArgs, MeshContext>,
  /** null **/
  changePromotionApplication: InContextSdkMethod<Mutation['changePromotionApplication'], MutationchangePromotionApplicationArgs, MeshContext>,
  /** null **/
  editShop: InContextSdkMethod<Mutation['editShop'], MutationeditShopArgs, MeshContext>
  };

  export type SubscriptionSdk = {
    
  };

  export type Context = {
      ["Shop"]: { Query: QuerySdk, Mutation: MutationSdk, Subscription: SubscriptionSdk },
      
    };
}
