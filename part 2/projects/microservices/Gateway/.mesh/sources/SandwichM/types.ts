// @ts-nocheck

import { InContextSdkMethod } from '@graphql-mesh/types';
import { MeshContext } from '@graphql-mesh/runtime';

export namespace SandwichMTypes {
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

export type SandwichQueryController = {
  allSandwich: Array<Sandwiches>;
  sandwichById?: Maybe<Sandwiches>;
};


export type SandwichQueryControllersandwichByIdArgs = {
  sandwichId: Scalars['Int'];
};

export type SandwichMutationController = {
  saveSandwich: Sandwiches;
  updateSandwich: Sandwiches;
  deleteSandwich: Scalars['String'];
};


export type SandwichMutationControllersaveSandwichArgs = {
  newSandwich: SandwichesInput;
};


export type SandwichMutationControllerupdateSandwichArgs = {
  updateSandwich: SandwichesInput;
};


export type SandwichMutationControllerdeleteSandwichArgs = {
  sandwichId: Scalars['Int'];
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

  export type QuerySdk = {
      /** null **/
  allSandwich: InContextSdkMethod<SandwichQueryController['allSandwich'], {}, MeshContext>,
  /** null **/
  sandwichById: InContextSdkMethod<SandwichQueryController['sandwichById'], SandwichQueryControllersandwichByIdArgs, MeshContext>
  };

  export type MutationSdk = {
      /** null **/
  saveSandwich: InContextSdkMethod<SandwichMutationController['saveSandwich'], SandwichMutationControllersaveSandwichArgs, MeshContext>,
  /** null **/
  updateSandwich: InContextSdkMethod<SandwichMutationController['updateSandwich'], SandwichMutationControllerupdateSandwichArgs, MeshContext>,
  /** null **/
  deleteSandwich: InContextSdkMethod<SandwichMutationController['deleteSandwich'], SandwichMutationControllerdeleteSandwichArgs, MeshContext>
  };

  export type SubscriptionSdk = {
    
  };

  export type Context = {
      ["SandwichM"]: { Query: QuerySdk, Mutation: MutationSdk, Subscription: SubscriptionSdk },
      
    };
}
