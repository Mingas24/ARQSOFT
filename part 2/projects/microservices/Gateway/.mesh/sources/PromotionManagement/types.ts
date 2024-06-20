// @ts-nocheck

import { InContextSdkMethod } from '@graphql-mesh/types';
import { MeshContext } from '@graphql-mesh/runtime';

export namespace PromotionManagementTypes {
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

export type InputPromotionDTO = {
  sandwichId?: InputMaybe<Scalars['ID']>;
  type?: InputMaybe<Scalars['String']>;
  shopId?: InputMaybe<Scalars['ID']>;
  percentage?: InputMaybe<Scalars['Float']>;
  startDate?: InputMaybe<Scalars['String']>;
  endDate?: InputMaybe<Scalars['String']>;
};

export type Mutation = {
  addPromotion?: Maybe<PromotionDTO>;
  deletePromotionById?: Maybe<Scalars['Int']>;
  editPromotion?: Maybe<Scalars['Int']>;
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

export type PromotionDTO = {
  promotionId?: Maybe<Scalars['ID']>;
  type?: Maybe<Scalars['String']>;
  sandwichId?: Maybe<Scalars['ID']>;
  shopId?: Maybe<Scalars['ID']>;
  percentage?: Maybe<Scalars['Float']>;
  startDate?: Maybe<Scalars['String']>;
  endDate?: Maybe<Scalars['String']>;
};

export type Query = {
  listPromotions?: Maybe<Array<Maybe<PromotionDTO>>>;
  getPromotionById?: Maybe<PromotionDTO>;
  getActiveLocalPromotionBySandwich?: Maybe<PromotionDTO>;
  getActiveGlobalPromotionBySandwich?: Maybe<PromotionDTO>;
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
  listPromotions: InContextSdkMethod<Query['listPromotions'], {}, MeshContext>,
  /** null **/
  getPromotionById: InContextSdkMethod<Query['getPromotionById'], QuerygetPromotionByIdArgs, MeshContext>,
  /** null **/
  getActiveLocalPromotionBySandwich: InContextSdkMethod<Query['getActiveLocalPromotionBySandwich'], QuerygetActiveLocalPromotionBySandwichArgs, MeshContext>,
  /** null **/
  getActiveGlobalPromotionBySandwich: InContextSdkMethod<Query['getActiveGlobalPromotionBySandwich'], QuerygetActiveGlobalPromotionBySandwichArgs, MeshContext>
  };

  export type MutationSdk = {
      /** null **/
  addPromotion: InContextSdkMethod<Mutation['addPromotion'], MutationaddPromotionArgs, MeshContext>,
  /** null **/
  deletePromotionById: InContextSdkMethod<Mutation['deletePromotionById'], MutationdeletePromotionByIdArgs, MeshContext>,
  /** null **/
  editPromotion: InContextSdkMethod<Mutation['editPromotion'], MutationeditPromotionArgs, MeshContext>
  };

  export type SubscriptionSdk = {
    
  };

  export type Context = {
      ["PromotionManagement"]: { Query: QuerySdk, Mutation: MutationSdk, Subscription: SubscriptionSdk },
      
    };
}
